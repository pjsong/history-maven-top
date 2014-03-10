package site.yahoo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import util.CodeListUtil;
import codelist.CodeFilter;
import bean.YahooDTO;
import dao.dal.DalClient;
/** 根据code下载数据，按照日期递增，保存到表**/
@Component("yahooHistoryRead")
public class YahooHistoryRead {
    private Logger logger  = LoggerFactory.getLogger(getClass());
    @Autowired 
    DalClient dalClient;
    @Autowired
    CodeFilter codeFilter;
//	http://finance.yahoo.com/q
//	http://ichart.finance.yahoo.com/table.csv?s=002121.SZ&d=8&e=30&f=2011&g=d&a=2&b=6&c=2007&ignore=.csv
	private static String url="http://ichart.finance.yahoo.com/table.csv";
	/** 根据code下载数据，按照日期递增，保存到表**/
	public void writeCodes(String codePrefix, String dbNo){
	    List<String> codeToCollect = codeFilter.getByKey(codePrefix);
	    for(String code:codeToCollect){
	        writeTextTable(CodeListUtil.transLocalToYahooCode(code), dbNo);
	    }
	}
	/** 根据code下载数据，按照日期递增，保存到表**/
	private void writeTextTable(String code, String dbNo){
        URLConnection uc;
        ArrayList<String> inputLineArr = new ArrayList<String>();
        try {
            uc = new URL(url + getParameter(code)).openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                inputLineArr.add(0, inputLine);
            }
            in.close();
        } catch (Exception e) {
//            logger.info("exception thrown:{}", e);
            return;
        }
        for(String inputLineStr:inputLineArr){
            writeDTOFromLine(inputLineStr, code, dbNo);
        }
	}


	private void writeDTOFromLine(String str, String code, String dbNo){
	    if(str == null || str.startsWith("Date")){
	        return;
	    }
	    String[] data = str.split(",");
	    if(data.length < 7){
	        return;
	    }
	    YahooDTO dto = new YahooDTO();
	    dto.setDbInstanceId(dbNo);
	    dto.setCreateDate(data[0]);
	    dto.setCode(code);
	    dto.setPriceOpen(Double.valueOf(data[1]));
	    dto.setPriceHigh(Double.valueOf(data[2]));
	    dto.setPriceLow(Double.valueOf(data[3]));
	    dto.setPriceClose(Double.valueOf(data[4]));
	    dto.setVolumn(Double.valueOf(data[5]));
	    dto.setAdjClose(Double.valueOf(data[6]));
	    dalClient.persist(dto);
	}
	
    private String getParameter(String code) {
        StringBuffer sb = new StringBuffer().append(String.format("?s=%s&ignore=.csv", code));
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("code", code);
        param.put("dbInstanceId", "00");
        Map ret = dalClient.queryForMap("yahoo.query_latest_by_code", param);
        if (ret.get("createDate") != null) {
            String date = (String) ret.get("createDate");
            String[] ymd = date.split("-");
            Calendar calendar = Calendar.getInstance();
            calendar.set(Integer.valueOf(ymd[0]), Integer.valueOf(ymd[1])-1, Integer.valueOf(ymd[2]));
            calendar.add(Calendar.DATE, 1);
            sb.append(String.format("&a=%s&b=%s&c=%s", calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.YEAR)));
        }
        return sb.toString();
    }
}
//http://www.gummy-stuff.org/Yahoo-data.htm	
//http://ichart.finance.yahoo.com/table.csv?s=002121.SZ&d=8&e=15&f=2011&g=d&a=2&b=6&c=2007&ignore=.csv
//s - This is where you can specify your stock quote, if you want to download stock quote for Microsoft, just enter it as 's=MSFT'
//a - This parameter is to get the input for the start month. '00' is for January, '01' is for February and so on.
//b - This parameter is to get the input for the start day, this one quite straight forward, '1' is for day one of the month, '2' is for second day of the month and so on.
//c - This parameter is to get the input for the start year
//d - This parameter is to get the input for end month, and again '00' is for January, '02' is for February and so on.
//e - This parameter is to get the input for the end day
//f - This parameter is to get the input for the end year
//g - This parameter is to specify the interval of the data you want to download. 'd' is for daily, 'w' is for weekly and 'm' is for monthly prices. The default is 'daily' if you ignore this parameter.
//With all the parameters above, you can now construct a URL to download historical prices for any stock quotes you want. But if you are going to download all historical prices for a stock quotes from day one onward (eg: Intel), you don't need to crack your head to look for information such as when is Intel went IPO. You just need to ignore the start and end date as follow:
//eg: http://ichart.finance.yahoo.com/table.csv?s=INTC
//If you only specify the start date and ignore the end date, it will download everything right from the start date until the most current prices.
//eg: http://ichart.finance.yahoo.com/table.csv?s=INTC&a=00&b=1&c=2000

