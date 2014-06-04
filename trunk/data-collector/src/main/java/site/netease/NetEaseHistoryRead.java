package site.netease;

import java.io.BufferedReader;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import util.CodeListUtil;
import bean.NetEaseDTO;
import codelist.CodeFilter;
import dao.dal.DalClient;

/** 根据code下载数据，按照日期递增，保存到表 **/

@Component("netEaseHistoryRead")
public class NetEaseHistoryRead {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    DalClient dalClient;
    @Autowired
    CodeFilter codeFilter;
    // http://finance.NetEase.com/q
    // http://quotes.money.163.com/service/chddata.html?code=1002121&start=20070212&end=20140303&fields=TCLOSE;HIGH;LOW;TOPEN;LCLOSE;CHG;PCHG;TURNOVER;VOTURNOVER;VATURNOVER;TCAP;MCAP
    private static String url = "http://quotes.money.163.com/service/chddata.html";
    private static String field = "&fields=TCLOSE;HIGH;LOW;TOPEN;LCLOSE;CHG;PCHG;TURNOVER;VOTURNOVER;VATURNOVER;TCAP;MCAP";

    /** 根据code下载数据，按照日期递增，保存到表 **/
    public void writeCodes(String codePrefix, String dbNo) {
        List<String> codeToCollect = codeFilter.getByKey(codePrefix);
        for (String code : codeToCollect) {
            writeTextTable(CodeListUtil.transLocalToNeteaseCode(code), dbNo);
        }
    }

    /** 根据code下载数据，按照日期递增，保存到表 **/
    private void writeTextTable(String code, String dbNo) {
        URLConnection uc;
        ArrayList<String> inputLineArr = new ArrayList<String>();
        try {
            uc = new URL(url + getParameter(code)).openConnection();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(uc.getInputStream(), Charset.forName("GB2312")));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                inputLineArr.add(0, inputLine);
            }
            in.close();
        } catch (Exception e) {
            // logger.info("exception thrown:{}", e);
            return;
        }
//        writeDTOFromLine(inputLineArr, code, dbNo);
        writeDTOFromLineBatch(inputLineArr, code, dbNo);
    }

    private NetEaseDTO getObjectFromString(String str, String code, String dbNo) {
        if (str == null || str.startsWith("日期")) {
            return null;
        }
        String[] data = str.split(",");
        if (data.length < 15) {
            return null;
        }
        NetEaseDTO dto = new NetEaseDTO();
        dto.setDbInstanceId(dbNo);
        dto.setCreateDate(data[0]);
        dto.setCode(code);
        dto.setPriceOpen(Double.valueOf(toBeTrans(data[6])));
        dto.setPriceHigh(Double.valueOf(toBeTrans(data[4])));
        dto.setPriceLow(Double.valueOf(toBeTrans(data[5])));
        dto.setPriceClose(Double.valueOf(toBeTrans(data[3])));
        dto.setVolumn(Long.valueOf(toBeTrans(data[11])));
        dto.setChgRate(Double.valueOf(toBeTrans(data[10])));
        dto.setTotalMarketValue(Double.valueOf(toBeTrans(data[13])));
        dto.setCurrentMarketValue(Double.valueOf(toBeTrans(data[14])));
        return dto;
    }

    private void writeDTOFromLineBatch(ArrayList<String> inputLineArr, String code, String dbNo) {
        if(inputLineArr.size() == 1){
            return;
        }
        ArrayList al = new ArrayList<NetEaseDTO>();
        for (String inputLineStr : inputLineArr) {
            NetEaseDTO dto = getObjectFromString(inputLineStr, code, dbNo);
            if (dto == null) {
                continue;
            }
            al.add(dto);
        }
        if (!tryPersistBatch(al, 3)) {
            throw new RuntimeException();
        }
    }

    private void writeDTOFromLine(ArrayList<String> inputLineArr, String code, String dbNo) {
        if(inputLineArr.size() == 0 || inputLineArr.size() == 1  ){
            return;
        }
        for (String inputLineStr : inputLineArr) {
            NetEaseDTO dto = getObjectFromString(inputLineStr, code, dbNo);
            if (dto == null) {
                return;
            }
            if (!tryPersist(dto, 3)) {
                throw new RuntimeException();
            }
        }
    }

    private String toBeTrans(String data) {
        return StringUtils.isBlank(data) ? "0" : data;
    }

    private boolean tryPersistBatch(ArrayList dtoList, int times) {
        if( dtoList.size() == 0){
            return true;
        }
        boolean succeedFlag = false;
        while (times > 0) {
            try {
                dalClient.persistList(dtoList.get(0), dtoList);
                succeedFlag = true;
            } catch (Exception e) {
                times--;
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e1) {
                }
            }
            if (succeedFlag) {
                return true;
            }
        }
        return false;
    }
    
    private boolean tryPersist(NetEaseDTO dto, int times) {
        boolean succeedFlag = false;
        while (times > 0) {
            try {
                dalClient.persist(dto);
                succeedFlag = true;
            } catch (Exception e) {
                times--;
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e1) {
                }
            }
            if (succeedFlag) {
                return true;
            }
        }
        return false;
    }

    /** start month, day, year **/
    private String getParameter(String code) {
        StringBuffer sb = new StringBuffer().append(String.format("?code=%s", code));
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("code", code);
        param.put("dbInstanceId", "00");
        Map ret = dalClient.queryForMap("netease.query_latest_by_code", param);
        if (ret.get("createDate") != null) {
            String date = (String) ret.get("createDate");
            String[] ymd = date.split("-");
            Calendar calendar = Calendar.getInstance();
            calendar.set(Integer.valueOf(ymd[0]), Integer.valueOf(ymd[1]) - 1, Integer.valueOf(ymd[2]));
            calendar.add(Calendar.DATE, 1);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String start = sdf.format(calendar.getTime());
            String end = sdf.format(new Date());
            sb.append(String.format("&start=%s&end=%s", start, end));
        }
        return sb.toString();
    }
}
