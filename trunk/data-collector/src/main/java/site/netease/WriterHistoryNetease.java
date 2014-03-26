package site.netease;

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
import bean.NetEaseDTO;
import bean.NetEaseStatisticDTO;
import bean.NetEaseDTO;
import bean.NetEaseStatisticDTO;
import codelist.CodeFilter;
import dao.dal.DalClient;

@Component("writerHistoryNetease")
public class WriterHistoryNetease {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    DalClient dalClient;
    @Autowired
    CodeFilter codeFilter;
    private Integer[] upDownArr = new Integer[]{13,21,34,55,89,144,233,377};

    /**对code遍历，递归遍历createDate**/
    public void doHistoryCode(String codePrefix, String dbNo){
        List<String> codeToCollect = codeFilter.getByKey(codePrefix);
        for(String code:codeToCollect){
            Map param = new HashMap<String, String>();
            param.put("code", CodeListUtil.transLocalToYahooCode(code));
            param.put("dbInstanceId", dbNo);
            List<NetEaseDTO> list_future = dalClient.queryForList("netease.query_price_by_code_asc", param,NetEaseDTO.class);
            List<NetEaseDTO> list_history = new ArrayList<NetEaseDTO>();
            if(list_future == null || list_future.size() == 0){
                continue;
            }
            for (int i = list_future.size() - 1; i >= 0; i--) {
                NetEaseDTO netEaseDTO = list_future.get(i);
                list_future.remove(netEaseDTO);
                doHistory(netEaseDTO, list_future, list_history, dbNo);
                //list_history从当前往历史走
                list_history.add(0, netEaseDTO);
            }
        }
    }

    /**
     * code sample:002121.SZ
     * **/
    private void doHistory(NetEaseDTO yhDTO, List<NetEaseDTO> list_future, List<NetEaseDTO> list_history, String dbNo) {

        for (Integer step : upDownArr) {
            writeUpDownStats(yhDTO, list_future,step, true, dbNo);
            writeUpDownStats(yhDTO, list_future,step, false, dbNo);
        }
        writeHighLowInDaysStats(yhDTO, list_history,dbNo);
    }

    private void writeHighLowInDaysStats(NetEaseDTO currentDTO, List<NetEaseDTO> list_history, String dbNo){
        String str = WriterHistoryNeteaseHelper.inDaysHighLowWrite(currentDTO, list_history, dbNo);
        NetEaseStatisticDTO statsDTO = getCurrentStatisticsDTO(currentDTO, dbNo);
        String[] highLow = str.split(";");
        if(statsDTO == null){
            statsDTO = new NetEaseStatisticDTO();
            statsDTO.setCode(currentDTO.getCode());
            statsDTO.setCreatedDate(currentDTO.getCreateDate());
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
    
   
    private void writeUpDownStats(NetEaseDTO currentDTO, List<NetEaseDTO> listFuture,  int percentageStep, boolean isUp, String dbNo){
        String code = currentDTO.getCode();
        String createDate = currentDTO.getCreateDate();
        int str = WriterHistoryNeteaseHelper.upDownWrite(currentDTO, listFuture, percentageStep, isUp);
        NetEaseStatisticDTO statsDTO = getCurrentStatisticsDTO(currentDTO, dbNo);
        if(statsDTO == null){
            statsDTO = new NetEaseStatisticDTO();
            statsDTO.setCode(code);
            statsDTO.setCreatedDate(createDate);
            statsDTO.setDbInstanceId(dbNo);
            if(isUp){
                statsDTO.writeUpDownField("setUp"+percentageStep, str);
            }else{
                statsDTO.writeUpDownField("setDown"+percentageStep, str);
            }
            dalClient.persist(statsDTO);
        } else if (isUp && 
                (statsDTO.readUpDownField("getUp" + percentageStep) == null 
                || !statsDTO.readUpDownField("getUp" + percentageStep).toString().equals(str))) {
            statsDTO.setDbInstanceId(dbNo);
            statsDTO.writeUpDownField("setUp" + percentageStep, str);
            dalClient.dynamicMerge(statsDTO);
        } else if (!isUp && 
                (statsDTO.readUpDownField("getDown" + percentageStep) == null 
                || !statsDTO.readUpDownField("getDown" + percentageStep).toString().equals(str))) {
            statsDTO.setDbInstanceId(dbNo);
            statsDTO.writeUpDownField("setDown" + percentageStep, str);
            dalClient.dynamicMerge(statsDTO);
        }
    }
    /**指定步长的数据**/
//    private void writeStep1Stats(NetEaseDTO currentDTO, List<NetEaseDTO> list_desc, int step, ArrayList<Integer> breakPointArr, String dbNo){
//        
//        NetEaseStatisticDTO statsDTO = getCurrentStatisticsDTO(currentDTO, dbNo);
//        String str = WriterHistoryNeteaseHelper.step1Write(WriterHistoryNeteaseHelper.step1PickUp(currentDTO, list_desc, step));
//        if(statsDTO == null){
//            statsDTO = new NetEaseStatisticDTO();
//            statsDTO.setCode(currentDTO.getCode());
//            statsDTO.setCreatedDate(currentDTO.getCreateDate());
//            statsDTO.setDbInstanceId(dbNo);
//            statsDTO.writeStepField("setStep"+step, str);
//            dalClient.persist(statsDTO);
//        }else if(StringUtils.isBlank(statsDTO.readStepField("getStep"+step))){
//            statsDTO.setDbInstanceId(dbNo);
//            statsDTO.writeStepField("setStep"+step, str);
//            dalClient.dynamicMerge(statsDTO);
//        }
//    }
    /** get dto by code and date**/
//  SELECT * from yahoo_statistics WHERE code= :code and createDate = :createDate order by createDate desc
    private NetEaseStatisticDTO getCurrentStatisticsDTO(NetEaseDTO currentDTO,  String dbNo){
        String code = currentDTO.getCode();
        String createDate = currentDTO.getCreateDate();
        Map param = new HashMap<String, String>();
        param.put("code", code);
        param.put("dbInstanceId", dbNo);
        param.put("createDate", createDate);
//        SELECT * from yahoo_statistics WHERE code= :code and createDate = :createDate order by createDate desc
        return dalClient.queryForObject("yahoo.query_statistics_by_code_date_stats", param,
                NetEaseStatisticDTO.class);
    }
}
