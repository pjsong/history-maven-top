package site.sohu;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import bean.SohuDTO;
import bean.SohuHistoryExceptionDTO;
import bean.SohuMinMaxDTO;
import util.CodeListUtil;
import codelist.CodeFilter;
import dao.dal.DalClient;

/** 根据code下载数据，按照日期递增，保存到表 **/
@Component("sohuHistoryRead")
public class SohuHistoryRead {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    DalClient dalClient;
    @Autowired
    CodeFilter codeFilter;
    String elementId = "BIZ_hq_historySearch";
    private static String url = "http://q.stock.sohu.com/cn/";
    private static String defaultStartDate = "1991-01-01";
    private static String defaultEndDate = "2070-01-01";
    private static String exceptionReason1 = "one page load times out";
    /**
     * to prevent finished codes open page, compares system date with latest record date(startDateWithinDays) to tell if
     * it's within
     **/
    private static int startDateWithinNDays = 3;

    // /** if the latest record time is different from others, ignore it. **/
    // private String commonLatestDate = "2014-02-21";

    /** 根据code下载数据，按照日期递增，保存到表 **/
    public void writeCodes(String codePrefix, String dbNo) {
        List<String> codeToCollect = codeFilter.getByKey(codePrefix);
        List<SohuMinMaxDTO> minMaxList = initMinMaxDateOfCode(codeToCollect);
        WebDriver driver = new FirefoxDriver();
        for (String code : codeToCollect) {
            writeTextTable(driver, CodeListUtil.transLocalToSohuCode(code), minMaxList, dbNo);
        }
        driver.quit();
    }

    /** 根据code下载数据，按照日期递增，保存到表 **/
    private void writeTextTable(WebDriver driver, String code, List<SohuMinMaxDTO> minMaxList, String dbNo) {
        String endDate = getParameter(minMaxList, code)[1];
        if (startDateWithinNDays(getCalendarByStr(endDate), startDateWithinNDays)) {
            return;
        }
        if (endDate.equals(defaultEndDate)) {
            doTwoPage(code, driver, dbNo);
            return;
        } else {
            doOnePage(endDate, code, driver, dbNo);
        }
 
    }

    /**
     * data0 createDate
     * 
     * 
     * **/
    private void writeDTOFromTr(Element tr, String code, String dbNo) {
        StringBuilder sb = new StringBuilder();
        Elements tds = tr.getElementsByTag("td");
        for (Element td : tds) {
            sb.append(td.ownText() + ",");
        }
        String str = sb.toString();
        if (StringUtils.isBlank(str) || str.startsWith("累计")) {
            return;
        }
        sb.deleteCharAt(sb.lastIndexOf(","));

        String[] data = str.split(",");
        if (data.length != 10) {
            return;
        }
        SohuDTO dto = new SohuDTO();
        dto.setDbInstanceId(dbNo);
        dto.setCreateDate(data[0]);
        dto.setCode(code);
        dto.setPriceOpen(Double.valueOf(data[1]));
        dto.setPriceClose(Double.valueOf(data[2]));
        dto.setPriceLow(Double.valueOf(data[5]));
        dto.setPriceHigh(Double.valueOf(data[6]));
        dto.setVolumn(Double.valueOf(data[7]));
        if (Double.valueOf(data[7]) > 0) {
            dto.setAdjClose(new BigDecimal(100 * Double.valueOf(data[8]) / Double.valueOf(data[7])).setScale(2, 4)
                    .doubleValue());
        }
        dalClient.persist(dto);
    }

    /** start is the min time in db, end is max time in db,return formatted as [min, max+1] or [start, end+1] **/
    private String[] getParameter(List<SohuMinMaxDTO> minMaxList, String code) {
        String startDate = defaultStartDate;
        String endDate = defaultEndDate;
        /** find in memory first **/
        for (SohuMinMaxDTO minMax : minMaxList) {
            if (minMax.getCode().equals(code)) {
                startDate = minMax.getMinDate();
                Calendar calendarMax = getCalendarByStr(minMax.getMaxDate());
                calendarMax.add(Calendar.DAY_OF_MONTH, 1);
                endDate = new SimpleDateFormat("yyyy-MM-dd").format(calendarMax.getTime());
                return new String[] { startDate, endDate };
            }
        }
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("code", code);
        param.put("dbInstanceId", "00");
        Map ret = dalClient.queryForMap("sohu.query_latest_by_code", param);
        if (ret.get("createDate") != null) {
            endDate = (String) ret.get("createDate");
            startDate = (String) ret.get("minCreateDate");
            SohuMinMaxDTO toBeInserted = new SohuMinMaxDTO();
            toBeInserted.setDbInstanceId("00");
            toBeInserted.setCode(code);
            toBeInserted.setMaxDate(endDate);
            toBeInserted.setMinDate(startDate);
            dalClient.persist(toBeInserted);
            Calendar cal_end = getCalendarByStr(endDate);
            cal_end.add(Calendar.DAY_OF_MONTH, 1);
            endDate = new SimpleDateFormat("yyyy-MM-dd").format(cal_end.getTime());
        } else {
            SohuMinMaxDTO toBeInserted = new SohuMinMaxDTO();
            toBeInserted.setDbInstanceId("00");
            toBeInserted.setCode(code);
            toBeInserted.setMaxDate(endDate);
            toBeInserted.setMinDate(startDate);
            dalClient.persist(toBeInserted);
        }
        return new String[] { startDate, endDate };
    }

    private Calendar getCalendarByStr(String timeStr) {
        String[] ymd = timeStr.split("-");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.valueOf(ymd[0]), Integer.valueOf(ymd[1]) - 1, Integer.valueOf(ymd[2]));
        return calendar;
    }

    /**
     * if startDate is less than N days to currentTime, skip this code; if so, return
     * **/
    private boolean startDateWithinNDays(Calendar startDate, int days) {
        if (new SimpleDateFormat("yyyy-MM-dd").format(startDate.getTime()).equals(defaultEndDate)) {
            return false;
        }
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DAY_OF_MONTH, 0 - days);
        return startDate.after(now);
    }

    /** for efficiency consideration, store min and max date of each code **/
    private List<SohuMinMaxDTO> initMinMaxDateOfCode(List<String> codeToCollect) {
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("dbInstanceId", "00");
        List<SohuMinMaxDTO> ret = dalClient.queryForList("sohu.load_min_max_all_code", param, SohuMinMaxDTO.class);
        if (ret.size() == 0) {
            for (String code : codeToCollect) {
                code = CodeListUtil.transLocalToSohuCode(code);
                SohuMinMaxDTO toBeInserted = new SohuMinMaxDTO();
                toBeInserted.setDbInstanceId("00");
                toBeInserted.setCode(code);
                param.clear();
                param.put("code", code);
                param.put("dbInstanceId", "00");
                Map maxMin = dalClient.queryForMap("sohu.query_latest_by_code", param);
                String max = (String) maxMin.get("createDate");
                String min = (String) maxMin.get("minCreateDate");
                /** filter out invalid values **/
                if (max == null && min == null) {
                    continue;
                }
                toBeInserted.setMaxDate(max);
                toBeInserted.setMinDate(min);
                dalClient.persist(toBeInserted);
                ret.add(toBeInserted);
            }
        }
        return ret;
    }

    private void doOnePage(String endDate, String code, WebDriver driver, String dbNo) {
        driver.get(url + String.format("%s/lshq.shtml", code));

        String ret = driver.getPageSource();
        Document doc = Jsoup.parse(ret);
        Elements trs = null;
        try {
            trs = doc.getElementById("BIZ_hq_historySearch").select("tbody").select("tr");
        } catch (NullPointerException e) {
            SohuHistoryExceptionDTO ex = new SohuHistoryExceptionDTO();
            ex.setDbInstanceId(dbNo);
            ex.setCode(code);
            ex.setReason("NullPointerException");
            dalClient.persist(ex);
            return;
        }
        ArrayList<Element> sortedElements = new ArrayList<Element>();
        String latestRecord = null;
        for (Element tr : trs) {
            Elements tds = tr.getElementsByTag("td");
            String openTxt = tds.get(0).ownText();
            if (StringUtils.isBlank(openTxt) || openTxt.startsWith("累计")) {
                continue;
            }
            if (endDate.compareTo(openTxt) == 1) {
                break;
            }
            if (latestRecord == null) {
                latestRecord = openTxt;
            }
            sortedElements.add(0, tr);
        }
        Element trTemp = null;
        try {
            for (Element tr : sortedElements) {
                trTemp = tr;
                writeDTOFromTr(tr, code, dbNo);
            }
        } catch (DuplicateKeyException e) {
            SohuHistoryExceptionDTO ex = new SohuHistoryExceptionDTO();
            ex.setDbInstanceId(dbNo);
            ex.setCode(code);
            Elements tds = trTemp.getElementsByTag("td");
            ex.setStartDate(tds.get(0).ownText());
            ex.setReason("DuplicateKeyException");
            dalClient.persist(ex);
            return;
        }
        if (latestRecord != null) {
            HashMap<String, String> param = new HashMap<String, String>();
            param.put("dbInstanceId", "00");
            param.put("code", code);
            param.put("date", latestRecord);
            dalClient.execute("sohu.update_min_max_by_code", param);
        }
    }

    private void doTwoPage(String code, WebDriver driver, String dbNo) {
        // Alternatively the same thing can be done like this
        // driver.navigate().to("http://www.google.com");
        driver.get(url + String.format("%s/lshq.shtml", code));
        String firstRecord = null;
        String latestRecord = null;
        try {
            WebElement element = driver.findElement(By.id("BIZ_lshq_sd"));
            element.clear();
            element.sendKeys(defaultStartDate);
            WebElement element1 = driver.findElement(By.id("BIZ_lshq_ed"));
            element1.clear();
            element1.sendKeys(defaultEndDate);
            List<WebElement> buttons = driver.findElements(By.tagName("input"));
            for (WebElement we : buttons) {
                if ("button".equalsIgnoreCase(we.getAttribute("type")) && "查询".equals(we.getAttribute("value"))) {
                    try {
                        we.click();
                    } catch (Exception e) {
                        return;
                    }
                }
            }
            /** if element not found or other exceptions occur, try repeatedly **/
            (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver d) {
                    return d.findElement(By.id("BIZ_hq_historySearch")) != null;
                }
            });
            String ret = driver.getPageSource();
            Document doc = Jsoup.parse(ret);
            Elements trs = doc.getElementById("BIZ_hq_historySearch").select("tbody").select("tr");
            ArrayList<Element> sortedElements = new ArrayList<Element>();
            for (int i = 0; i < trs.size(); i++) {
                Element tr = trs.get(i);
                Elements tds = tr.getElementsByTag("td");
                String openTxt = tds.get(0).ownText();
                if (StringUtils.isBlank(openTxt) || openTxt.startsWith("累计")) {
                    continue;
                }
                if (latestRecord == null) {
                    latestRecord = openTxt;
                }
                firstRecord = openTxt;
                sortedElements.add(0, tr);
            }
            for (Element tr : sortedElements) {
                writeDTOFromTr(tr, code, dbNo);
            }

        } catch (Exception e) {
            logger.info("exception thrown:{}", e);
            return;
        } finally {
            if (latestRecord != null || firstRecord != null) {
                HashMap<String, String> param = new HashMap<String, String>();
                param.put("dbInstanceId", "00");
                param.put("code", code);
                SohuMinMaxDTO ret = dalClient.queryForObject("sohu.query_min_max_by_code", param, SohuMinMaxDTO.class);
                if(ret == null || ret.getCode() == null)
                {
                SohuMinMaxDTO toBeInserted = new SohuMinMaxDTO();
                toBeInserted.setDbInstanceId("00");
                toBeInserted.setCode(code);
                toBeInserted.setMaxDate(latestRecord);
                toBeInserted.setMinDate(firstRecord);
                dalClient.persist(toBeInserted);
                }else{
                    ret.setDbInstanceId("00");
                    ret.setMaxDate(latestRecord);
                    ret.setMinDate(firstRecord);
                    dalClient.merge(ret);
                }
            }
        }

    }
}