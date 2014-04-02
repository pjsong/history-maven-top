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
import bean.NetEaseMainFinancialDTO;
import codelist.CodeFilter;
import dao.dal.DalClient;

/** 根据code下载数据，按照日期递增，保存到表 **/

@Component("netEaseMainFinancialRead")
public class NetEaseMainFinancialRead {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    DalClient dalClient;
    @Autowired
    CodeFilter codeFilter;
    // http://finance.NetEase.com/q
    // http://quotes.money.163.com/service/chddata.html?code=1002121&start=20070212&end=20140303&fields=TCLOSE;HIGH;LOW;TOPEN;LCLOSE;CHG;PCHG;TURNOVER;VOTURNOVER;VATURNOVER;TCAP;MCAP
    private static String url = "http://quotes.money.163.com/service/zycwzb_%s.html?type=report";

    /** 根据code下载数据，按照日期递增，保存到表 **/
    public void writeCodes(String codePrefix, String dbNo) {
        List<String> codeToCollect = codeFilter.getByKey(codePrefix);
        for (String code : codeToCollect) {
            writeTextTable(CodeListUtil.transLocalToSohuCode(code), dbNo);
        }
    }

    /** 根据code下载数据，按照日期递增，保存到表 **/
    private void writeTextTable(String code, String dbNo) {
        URLConnection uc;
        ArrayList<String> inputLineArr = new ArrayList<String>();
        try {
            uc = new URL(String.format(url, code)).openConnection();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(uc.getInputStream(), Charset.forName("GB2312")));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                inputLineArr.add(inputLine);
            }
            in.close();
        } catch (Exception e) {
            // logger.info("exception thrown:{}", e);
            return;
        }
        writeDTOFromLine(inputLineArr, code, dbNo);
        // writeDTOFromLineBatch(inputLineArr, code, dbNo);
    }

    private NetEaseMainFinancialDTO getObjectFromString(String str, String code, String dbNo) {
        if (str == null || str.startsWith("报告日期")) {
            return null;
        }
        String[] data = str.split(",");
        if (data.length < 15) {
            return null;
        }
        NetEaseMainFinancialDTO dto = new NetEaseMainFinancialDTO();
        dto.setDbInstanceId(dbNo);
        dto.setCreateDate(data[0]);
        dto.setCode(code);

        dto.setBusinessInterest(Double.valueOf(toBeTrans(data[6])));
        dto.setCashGenPerShare(Double.valueOf(toBeTrans(data[6])));
        dto.setCashGenTotal(Double.valueOf(toBeTrans(data[6])));
        dto.setDebtFlow(Double.valueOf(toBeTrans(data[6])));
        dto.setDebtTotal(Double.valueOf(toBeTrans(data[6])));
        dto.setMainBusinessIncome(Double.valueOf(toBeTrans(data[6])));
        dto.setMainBusinessInterest(Double.valueOf(toBeTrans(data[6])));
        dto.setPureInterest(Double.valueOf(toBeTrans(data[6])));
        dto.setShareHolderBig(Double.valueOf(toBeTrans(data[6])));
        dto.setTotalInterest(Double.valueOf(toBeTrans(data[6])));

        return dto;
    }

    private void writeDTOFromLineBatch(ArrayList<String> inputLineArr, String code, String dbNo) {
//        if (inputLineArr.size() == 1) {
//            return;
//        }
//        ArrayList al = new ArrayList<NetEaseMainFinancialDTO>();
//        for (String inputLineStr : inputLineArr) {
//            NetEaseMainFinancialDTO dto = getObjectFromString(inputLineStr, code, dbNo);
//            if (dto == null) {
//                continue;
//            }
//            al.add(dto);
//        }
//        if (!tryPersistBatch(al, 3)) {
//            throw new RuntimeException();
//        }
    }

    private List<NetEaseMainFinancialDTO> writeDTOFromLine(ArrayList<String> inputLineArr, String code, String dbNo) {
        if (inputLineArr.size() == 0 || inputLineArr.size() == 1) {
            return null;
        }
        ArrayList<NetEaseMainFinancialDTO> ret = new ArrayList<NetEaseMainFinancialDTO>();
        ArrayList<String> rewrittenArr = new ArrayList<String>();
        for (int i = 0;i<inputLineArr.size();i++) {
            String inputLineStr = inputLineArr.get(i);
            String[] inputLineStrArr = inputLineStr.split(",");
            StringBuffer stringI = new StringBuffer();
            for(int j = 0;j< inputLineStrArr.length;j++){
                stringI.append(","+inputLineStrArr[j]);
            }
            rewrittenArr.add(stringI.deleteCharAt(0).toString());
        }
        for(String s:rewrittenArr){
            String[] data = s.split(",");
            NetEaseMainFinancialDTO dto = new NetEaseMainFinancialDTO();
            dto.setDbInstanceId(dbNo);
            dto.setCreateDate(data[0]);
            dto.setCode(code);

            dto.setBusinessInterest(Double.valueOf(toBeTrans(data[6])));
            dto.setCashGenPerShare(Double.valueOf(toBeTrans(data[6])));
            dto.setCashGenTotal(Double.valueOf(toBeTrans(data[6])));
            dto.setDebtFlow(Double.valueOf(toBeTrans(data[6])));
            dto.setDebtTotal(Double.valueOf(toBeTrans(data[6])));
            dto.setMainBusinessIncome(Double.valueOf(toBeTrans(data[6])));
            dto.setMainBusinessInterest(Double.valueOf(toBeTrans(data[6])));
            dto.setPureInterest(Double.valueOf(toBeTrans(data[6])));
            dto.setShareHolderBig(Double.valueOf(toBeTrans(data[6])));
            dto.setTotalInterest(Double.valueOf(toBeTrans(data[6])));
        }

        return ret;
    }

    private String toBeTrans(String data) {
        return StringUtils.isBlank(data) ? "0" : data;
    }

    private boolean tryPersistBatch(ArrayList dtoList, int times) {
        if (dtoList.size() == 0) {
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

    private boolean tryPersist(NetEaseMainFinancialDTO dto, int times) {
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

}
