package site.sina.rt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import util.CodeListUtil;
import bean.ExceptionsLive;
import bean.SinaLive;
import codelist.CodeFilter;
import dao.dal.DalClient;

@Component("writerFromSite")
public class WriterFromSite {
    private Logger logger  = LoggerFactory.getLogger(getClass());
	@Autowired 
	DalClient dalClient;
	@Autowired
	CodeFilter codeFilter;
	public void doCollect(String codePrefix, String dbNo) throws IOException{
		List<String> codeToCollect = codeFilter.getByKey(codePrefix);
		String codeStr = CodeListUtil.transCodeListToStr(codeToCollect);
		String fetchUrl = String.format("http://hq.sinajs.cn/list=%s", codeStr);
		URLConnection uc;
		try {
			uc = new URL(fetchUrl).openConnection();
		} catch (Exception e) {
			return;
		}
		BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			SinaLive sinaLive = parseLine(inputLine, dbNo);
			if(sinaLive != null){
				dalClient.persist(sinaLive);
			}
            logger.debug(inputLine.split("=")[0].substring(11) + " succeed");
		}
	}
	
	//sample line http://hq.sinajs.cn/list=sh601006
	//var hq_str_sh601006="大秦铁路,7.20,7.25,7.25,7.40,7.20,7.24,7.26,45732486,333443552,7000,7.24,38401,7.23,72456,7.22,129536,7.21,167800,7.20,171400,7.26,32800,7.27,120200,7.28,49500,7.29,62080,7.30,2013-11-15,15:04:07,00";
//	0：”大秦铁路”，股票名字；
//	1：”27.55″，今日开盘价；
//	2：”27.25″，昨日收盘价；
//	3：”26.91″，当前价格；
//	4：”27.55″，今日最高价；
//	5：”26.20″，今日最低价；
//	6：”26.91″，竞买价，即“买一”报价；
//	7：”26.92″，竞卖价，即“卖一”报价；
//	8：”22114263″，成交的股票数，由于股票交易以一百股为基本单位，所以在使用时，通常把该值除以一百；
//	9：”589824680″，成交金额，单位为“元”，为了一目了然，通常以“万元”为成交金额的单位，所以通常把该值除以一万；
//	10：”4695″，“买一”申请4695股，即47手；
//	11：”26.91″，“买一”报价；
//	12：”57590″，“买二”
//	13：”26.90″，“买二”
//	14：”14700″，“买三”
//	15：”26.89″，“买三”
//	16：”14300″，“买四”
//	17：”26.88″，“买四”
//	18：”15100″，“买五”
//	19：”26.87″，“买五”
//	20：”3100″，“卖一”申报3100股，即31手；
//	21：”26.92″，“卖一”报价
//	(22, 23), (24, 25), (26,27), (28, 29)分别为“卖二”至“卖四的情况”
//	30：”2008-01-11″，日期；
//	31：”15:05:32″，时间；
	private SinaLive parseLine(String line, String dbNo){
		if(line.indexOf("=") == -1){
			return null;
		}
		String[] codeDetail = line.split("=");
		String code = codeDetail[0].substring(11);
		String[] details = codeDetail[1].split(",");
		if(details.length < 32){
			ExceptionsLive exceptionsLive = new ExceptionsLive();
			exceptionsLive.setDbInstanceId(dbNo);
			exceptionsLive.setCode(code);
			exceptionsLive.setSource("sina");
			exceptionsLive.setMalFormedLine(line);
			dalClient.persist(exceptionsLive);
			return null;
		}
		String name = details[1];
		String openPriceToday = details[2];
		String closePriceYesterday = details[3];
		String closePriceToday = details[4];
		String highestToday = details[5];
		String lowestToday = details[6];
		String dealAmount = details[8];
		String dealMoney = details[9];
		String dealDate = details[30];
		String dealTime = details[31];
		SinaLive sinaLive = new SinaLive();
		sinaLive.setDbInstanceId("00");
		sinaLive.setCode(code);
		sinaLive.setName(name);
		sinaLive.setOpenPriceToday(Double.valueOf(openPriceToday));
		sinaLive.setClosePriceYesterday(Double.valueOf(closePriceYesterday));
		sinaLive.setClosePriceToday(Double.valueOf(closePriceToday));
		sinaLive.setHighestToday(Double.valueOf(highestToday));
		sinaLive.setLowestToday(Double.valueOf(lowestToday));
		sinaLive.setDealAmount(Double.valueOf(dealAmount));
		sinaLive.setDealMoney(Double.valueOf(dealMoney));
//		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
//		SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");
        sinaLive.setCreateDate(dealDate);
        sinaLive.setCreateTime(dealTime);
		return sinaLive;
	}
}
