package site.yahoo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import util.CodeListUtil;
import bean.YahooDTO;
import bean.YahooStatisticDTO;
import codelist.CodeFilter;
import dao.dal.DalClient;

@Component("writerHistoryYahoo")
public class WriterHistoryYahoo {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    DalClient dalClient;
    @Autowired
    CodeFilter codeFilter;
    private Integer[] stepArr = new Integer[]{1,2,3,5,8,13,21,34,55,89};
    private Integer[] upDownArr = new Integer[]{1,2,3,5,8,13,21,34,55,89,144,233,377,610};

    /**对code遍历，递归遍历createDate**/
    public void doHistoryCode(String codePrefix, String dbNo){
        List<String> codeToCollect = codeFilter.getByKey(codePrefix);
        for(String code:codeToCollect){
            //the last stats written
            YahooStatisticDTO ysDTO = getLatestCalculatedDay(code, dbNo);
            Map param = new HashMap<String, String>();
            param.put("code", CodeListUtil.transLocalToYahooCode(code));
            param.put("dbInstanceId", dbNo);
            List<YahooDTO> list_desc = dalClient.queryForList("yahoo.query_price_by_code_desc", param,YahooDTO.class);
            if(list_desc == null || list_desc.size() == 0){
                continue;
            }
            ArrayList<Integer> breakPointArr = WriterHistoryYahooHelper.breakPointIndex(list_desc);

            for (int i = list_desc.size() - 1; i >= 0; i--) {
                YahooDTO yhDTO = list_desc.get(i);
                //不处理时间小于当前日期的
                if(ysDTO != null && yhDTO.getCreateDate().compareTo(ysDTO.getCreatedDate()) != 1){
                    continue;
                }
                doHistory(yhDTO, list_desc, breakPointArr, dbNo);
            }
        }
    }
    /**找出最近已经计算的时间点**/
    private YahooStatisticDTO getLatestCalculatedDay(String code, String dbNo){
        Map param = new HashMap<String, String>();
        param.put("code", CodeListUtil.transLocalToYahooCode(code));
        param.put("dbInstanceId", dbNo);
        YahooStatisticDTO yDTO = dalClient.queryForObject("yahoo.query_latest_calculated_day", param, YahooStatisticDTO.class);
        return yDTO;
    }
    /**
     * code sample:002121.SZ
     * **/
    private void doHistory(YahooDTO yhDTO, List<YahooDTO> list_desc, ArrayList<Integer> breakPointArr, String dbNo) {
        String code = yhDTO.getCode();
        String createDate = yhDTO.getCreateDate();
        for(Integer step:stepArr){
            writeStep1Stats(list_desc,code, createDate, step, breakPointArr, dbNo);
        }
        for (Integer step : upDownArr) {
            writeUpDownStats(list_desc,code, createDate, step, breakPointArr, true, dbNo);
            writeUpDownStats(list_desc,code, createDate, step, breakPointArr, false, dbNo);
        }
        writeHighLowInDaysStats(list_desc,code, createDate, breakPointArr, dbNo);
    }

    private void writeHighLowInDaysStats(List<YahooDTO> list_desc, String code, String createDate, ArrayList<Integer> breakPointArr, String dbNo){
        String str = WriterHistoryYahooHelper.inDaysHighLowWrite(list_desc, code, createDate, breakPointArr, dbNo);
        YahooStatisticDTO statsDTO = getCurrentDTO(code, createDate, dbNo);
        String[] highLow = str.split(";");
        if(statsDTO == null){
            statsDTO = new YahooStatisticDTO();
            statsDTO.setCode(code);
            statsDTO.setCreatedDate(createDate);
            statsDTO.setDbInstanceId(dbNo);
            statsDTO.setHighestInDays(Integer.valueOf(highLow[0]));
            statsDTO.setLowestInDays(Integer.valueOf(highLow[1]));
            dalClient.persist(statsDTO);
        }else{
            statsDTO.setDbInstanceId(dbNo);
            statsDTO.setHighestInDays(Integer.valueOf(highLow[0]));
            statsDTO.setLowestInDays(Integer.valueOf(highLow[1]));
            dalClient.dynamicMerge(statsDTO);
        }
    }
    
    private void writeUpDownStats(List<YahooDTO> list, String code, String createDate, int percentageStep, ArrayList<Integer> breakPointArr, boolean isUp, String dbNo){
        int str = WriterHistoryYahooHelper.upDownWrite(WriterHistoryYahooHelper.upDownPickUp(list, code, createDate, breakPointArr), percentageStep, isUp);
        YahooStatisticDTO statsDTO = getCurrentDTO(code, createDate, dbNo);
        if(statsDTO == null){
            statsDTO = new YahooStatisticDTO();
            statsDTO.setCode(code);
            statsDTO.setCreatedDate(createDate);
            statsDTO.setDbInstanceId(dbNo);
            if(isUp){
                statsDTO.writeUpDownField("setUp"+percentageStep, str);
            }else{
                statsDTO.writeUpDownField("setDown"+percentageStep, str);
            }
            dalClient.persist(statsDTO);
        }else if(isUp && (statsDTO.readUpDownField("getUp"+percentageStep)==null || statsDTO.readUpDownField("getUp"+percentageStep)==0)){
            statsDTO.setDbInstanceId(dbNo);
            statsDTO.writeUpDownField("setUp"+percentageStep, str);
            dalClient.dynamicMerge(statsDTO);
        }else if(!isUp && (statsDTO.readUpDownField("getDown"+percentageStep)==null || statsDTO.readUpDownField("getDown"+percentageStep) == 0)){
            statsDTO.setDbInstanceId(dbNo);
            statsDTO.writeUpDownField("setDown"+percentageStep, str);
            dalClient.dynamicMerge(statsDTO);
        }
    }
    /**去掉断点后，指定步长的数据**/
    private void writeStep1Stats(List<YahooDTO> list_desc, String code, String createDate, int step, ArrayList<Integer> breakPointArr, String dbNo){
        YahooStatisticDTO statsDTO = getCurrentDTO(code, createDate, dbNo);
        String str = WriterHistoryYahooHelper.step1Write(WriterHistoryYahooHelper.step1PickUp(list_desc, code, createDate, step, breakPointArr));
        if(statsDTO == null){
            statsDTO = new YahooStatisticDTO();
            statsDTO.setCode(code);
            statsDTO.setCreatedDate(createDate);
            statsDTO.setDbInstanceId(dbNo);
            statsDTO.writeStepField("setStep"+step, str);
            dalClient.persist(statsDTO);
        }else if(StringUtils.isBlank(statsDTO.readStepField("getStep"+step))){
            statsDTO.setDbInstanceId(dbNo);
            statsDTO.writeStepField("setStep"+step, str);
            dalClient.dynamicMerge(statsDTO);
        }
    }
    /** get dto by code and date**/
//  SELECT * from yahoo_statistics WHERE code= :code and createDate = :createDate order by createDate desc
    private YahooStatisticDTO getCurrentDTO(String code, String createDate, String dbNo){
        Map param = new HashMap<String, String>();
        param.put("code", code);
        param.put("dbInstanceId", dbNo);
        param.put("createDate", createDate);
//        SELECT * from yahoo_statistics WHERE code= :code and createDate = :createDate order by createDate desc
        return dalClient.queryForObject("yahoo.query_statistics_by_code_date_stats", param,
                YahooStatisticDTO.class);
    }
}
