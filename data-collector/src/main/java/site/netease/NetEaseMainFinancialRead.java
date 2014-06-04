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
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import util.CodeListUtil;
import bean.NetEaseDTO;
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
    }


    private void writeDTOFromLine(ArrayList<String> inputLineArr, String code, String dbNo) {
        if (inputLineArr.size() == 0 || inputLineArr.size() == 1) {
            return ;
        }
        String[] columnsInInputLine = inputLineArr.get(0).split(",");
        ArrayList<NetEaseMainFinancialDTO> ret = new ArrayList<NetEaseMainFinancialDTO>();
        
        ArrayList<String> rewrittenArr = new ArrayList<String>();
        //00报告日期； 01记录1； 10基本每股收益
        for (int i = 1;i<columnsInInputLine.length;i++) {
            StringBuffer sb = new StringBuffer();
            for(int j = 0;j< inputLineArr.size();j++){
                String[] inputColumn = inputLineArr.get(j).split(",");
                if(inputColumn.length != columnsInInputLine.length){
                    break;
                }
                sb.append(inputColumn[i]+",");
            }
            sb.deleteCharAt(sb.lastIndexOf(","));
            rewrittenArr.add(sb.toString());
        }
        for(String s:rewrittenArr){
            String[] data = s.split(",");
            
            Map param = new HashMap<String, String>();
            param.put("code", code);
            param.put("createDate", data[0]);
            param.put("dbInstanceId", dbNo);
            NetEaseMainFinancialDTO dto = dalClient.queryForObject("netease.query_main_financial_by_code_createDate", param,
                    NetEaseMainFinancialDTO.class);
            if(dto != null){
                break;
            }
            dto = new NetEaseMainFinancialDTO();
            dto.setDbInstanceId(dbNo);
            dto.setCreateDate(data[0]);
            dto.setCode(code);
            dto.setBasicBenefitPerShare(Double.valueOf(toBeTrans(data[1])));
            dto.setAssetsPerShare(Double.valueOf(toBeTrans(data[2])));
            dto.setBusinessInterest(Double.valueOf(toBeTrans(data[6])));
            dto.setCashGenPerShare(Double.valueOf(toBeTrans(data[3])));
            dto.setCashGenTotal(Double.valueOf(toBeTrans(data[13])));
            dto.setDebtFlow(Double.valueOf(toBeTrans(data[17])));
            dto.setDebtTotal(Double.valueOf(toBeTrans(data[16])));
            dto.setMainBusinessIncome(Double.valueOf(toBeTrans(data[4])));
            dto.setMainBusinessInterest(Double.valueOf(toBeTrans(data[5])));
            dto.setPureInterest(Double.valueOf(toBeTrans(data[10])));
            dto.setShareHolderBig(Double.valueOf(toBeTrans(data[18])));
            dto.setTotalInterest(Double.valueOf(toBeTrans(data[9])));
            dto.setAssetsTotal(Double.valueOf(toBeTrans(data[14])));
            dto.setAssetsFlow(Double.valueOf(toBeTrans(data[15])));
            ret.add(dto);
        }
        if (!tryPersistBatch(ret, 3)) {
            throw new RuntimeException();
        }
    }

    private String toBeTrans(String data) {
        return StringUtils.isBlank(data) || !NumberUtils.isNumber(data) ? "0" : data;
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
