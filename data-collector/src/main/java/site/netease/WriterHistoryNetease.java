package site.netease;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import util.CodeListUtil;
import bean.NetEaseDTO;
import bean.NetEaseStatisticDTO;
import codelist.CodeFilter;
import dao.dal.DalClient;

@Component("writerHistoryNetease")
public class WriterHistoryNetease {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    DalClient dalClient;
    int tryCount = 3;
    @Autowired
    CodeFilter codeFilter;
    private Integer[] upDownArr = new Integer[] { 13, 21, 34, 55, 89, 144, 233, 377 };
    private int countBegin = 378;
    private boolean newStart = true;
    /** 对code遍历，递归遍历createDate **/
    @SuppressWarnings("rawtypes")
    public void doHistoryCode(String codePrefix, String dbNo) {
        List<String> codeToCollect = codeFilter.getByKey(codePrefix);
        for (String code : codeToCollect) {
            Map param = new HashMap<String, String>();
            param.put("code", CodeListUtil.transLocalToNeteaseCode(code));
            param.put("dbInstanceId", dbNo);
            List<NetEaseDTO> list_all = dalClient.queryForList("netease.query_price_by_code_asc", param,
                    NetEaseDTO.class);

            Map<String, Object> map_statistics = dalClient.queryForMap(
                    "netease.query_statistics_by_code_asc_limit_last", param);
            Long count = (Long) map_statistics.get("count");
            if (count > countBegin) {
                count = count - countBegin;
            } else {
                count = 0l;
            }
            List<NetEaseDTO> list_history = new ArrayList<NetEaseDTO>();
            if (list_all == null || list_all.size() == 0) {
                continue;
            }
            int list_all_size = list_all.size();
            List<NetEaseDTO> list_future = new ArrayList<NetEaseDTO>();
            list_future.addAll(list_all);
            for (int i = 0; i < list_all_size; i++) {
                NetEaseDTO netEaseDTO = list_all.get(i);
                list_future.remove(0);
                if (i >= count && !newStart) {//from specified position
                    doHistory(netEaseDTO, list_future, list_history, dbNo);
                }else if(newStart){//from zero
                    doHistory(netEaseDTO, list_future, list_history, dbNo);
                }
                // list_history从当前往历史走
                list_history.add(0, netEaseDTO);
            }
        }
    }

    /**
     * 
     * **/
    private void doHistory(NetEaseDTO currentDTO, List<NetEaseDTO> list_future, List<NetEaseDTO> list_history,
            String dbNo) {
        NetEaseStatisticDTO statsDTO;
        NetEaseStatisticDTO statsDTO_current = getCurrentStatisticsDTO(currentDTO, dbNo);
        boolean oldDTOExists = statsDTO_current != null;
        if (oldDTOExists) {
            statsDTO = statsDTO_current;
            statsDTO.setDbInstanceId(dbNo);
        } else {
            statsDTO = new NetEaseStatisticDTO();
            statsDTO.setCode(currentDTO.getCode());
            statsDTO.setCreatedDate(currentDTO.getCreateDate());
            statsDTO.setDbInstanceId(dbNo);
        }
        for (Integer step : upDownArr) {
            // write up stats
            writeUpDownStats(statsDTO, currentDTO, list_future, step, true, dbNo);
            // write down stats
            writeUpDownStats(statsDTO, currentDTO, list_future, step, false, dbNo);
        }
        writeHighLowInDaysStats(statsDTO, currentDTO, list_history, dbNo);
        if (oldDTOExists) {
            boolean succeedFlag = false;
            tryCount = 3;
            while(tryCount>0){
                try{
                    dalClient.dynamicMerge(statsDTO);
                    succeedFlag = true;
                }catch(Exception e){
                    tryCount--;
                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e1) {
                    }
                }
                if (succeedFlag) {
                    break;
                }
            }
            
        } else {
            boolean succeedFlag = false;
            tryCount = 3;
            while(tryCount>0){
                try{
                    dalClient.persist(statsDTO);
                    succeedFlag = true;
                }catch(Exception e){
                    tryCount--;
                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e1) {
                    }
                }
                if (succeedFlag) {
                    break;
                }
            }
        }
    }

    private void writeHighLowInDaysStats(NetEaseStatisticDTO statsDTO, NetEaseDTO currentDTO,
            List<NetEaseDTO> list_history, String dbNo) {
        String str = WriterHistoryNeteaseHelper.inDaysHighLowWrite(currentDTO, list_history, dbNo);
        String[] highLow = str.split(";");
        if(highLow[0].equals("0")||highLow[1].equals("0")){
            return;
        }
        statsDTO.setHighestInDays(Integer.valueOf(highLow[0]));
        statsDTO.setLowestInDays(Integer.valueOf(highLow[1]));
    }

    /** isup: percentage up or down **/
    private void writeUpDownStats(NetEaseStatisticDTO statsDTO, NetEaseDTO currentDTO,
            List<NetEaseDTO> listFuture, int percentageStep, boolean isUp, String dbNo) {
        String code = currentDTO.getCode();
        String createDate = currentDTO.getCreateDate();
        int str = WriterHistoryNeteaseHelper.upDownWrite(currentDTO, listFuture, percentageStep, isUp);
        if(str == 0){
            return;
        }
        if (isUp) {
            statsDTO.writeUpDownField("setUp" + percentageStep, str);
        } else {
            statsDTO.writeUpDownField("setDown" + percentageStep, str);
        }

    }

    /** get dto by code and date **/
    // SELECT * from yahoo_statistics WHERE code= :code and createDate = :createDate order by createDate desc
    private NetEaseStatisticDTO getCurrentStatisticsDTO(NetEaseDTO currentDTO, String dbNo) {
        String code = currentDTO.getCode();
        String createDate = currentDTO.getCreateDate();
        Map param = new HashMap<String, String>();
        param.put("code", code);
        param.put("dbInstanceId", dbNo);
        param.put("createDate", createDate);
        NetEaseStatisticDTO ret = null;
        boolean succeedFlag = false;
        tryCount = 3;
        // SELECT * from yahoo_statistics WHERE code= :code and createDate = :createDate order by createDate desc
        while (tryCount > 0) {
            try {
                ret = dalClient.queryForObject("netease.query_statistics_by_code_date_stats", param,
                        NetEaseStatisticDTO.class);
                succeedFlag = true;
            } catch (Exception e) {
                tryCount--;
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e1) {
                }
            }
            if (succeedFlag) {
                return ret;
            }
        }
        return ret;
    }
}
