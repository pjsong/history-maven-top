package site.netease;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import util.CalcUtil;
import util.CodeListUtil;
import bean.NetEaseDTO;
import bean.NetEaseMainFinancialDTO;
import codelist.CodeFilter;
import dao.dal.DalClient;

/** 163股票分类 **/

@Component("netEaseClassifier")
public class NetEaseClassifier {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    DalClient dalClient;
    @Autowired
    CodeFilter codeFilter;
//    http://quotes.money.163.com/#query=dy001000&DataType=HS_RANK&sort=PERCENT&order=desc&count=500&page=0
    private static String urlArea = "http://quotes.money.163.com/#query=dy0%s000&count=1000&page=0";
//    http://stockapp.finance.qq.com/mstats/?pgv_ref=fi_quote_navi_bar#mod=list&id=bd_ind&module=BD&type=01

    
    /** http://jsoup.org/cookbook/extracting-data/example-list-links **/
    public void writeArea(String dbNo) throws IOException {
        for(int i = 1;i<32;i++){
            String areaCode = CalcUtil.transDigitToStr(i, 2);
            String urlToFetch = String.format(urlArea, areaCode);
            Document doc = Jsoup.connect(urlToFetch).timeout(1000*1000).get();
//            Document doc = Jsoup.parse(html);
            String selectStr  = " div #hsRank div div div.ID_panel.stocks-wrap";
            Elements elements = doc.select(".pages");
            System.out.println(elements);
            for(Element e:elements){
                System.out.println(e);
            }
            
        }
    }


}
