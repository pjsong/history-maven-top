package site.netease;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import util.CodeListUtil;
import bean.NetEaseLatestProfitPerShareDTO;
import codelist.CodeFilter;
import dao.dal.DalClient;

/** 根据code下载数据，按照日期递增，保存到表 **/

@Component
public class NetEaseLatestProfitPerShare {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    DalClient dalClient;
    @Autowired
    CodeFilter codeFilter;


    /** 根据code下载数据，按照日期递增，保存到表 **/
    public void writeCodes(String codePrefix, String dbNo) {
        List<String> codeToCollect = codeFilter.getByKey(codePrefix);
        for (String code : codeToCollect) {
            writeTextTable(CodeListUtil.transLocalToSohuCode(code), dbNo);
        }
    }

    /** 根据code下载数据，按照日期递增，保存到表 **/
    private void writeTextTable(String code, String dbNo) {
        Map param = new HashMap<String, String>();
        param.put("code", code);
        param.put("createDate", getCreateDate());
        param.put("dbInstanceId", dbNo);
        Map<String, Object> ret = dalClient.queryForMap("netease.query_latest_profit_perShare_by_code", param);
         NetEaseLatestProfitPerShareDTO dto = new NetEaseLatestProfitPerShareDTO();
         dto.setCode(code);
         dto.setDbInstanceId(dbNo);
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
         dto.setCreateDate(sdf.format(new Date()));
         dto.setBasicBenefitPerShare((Double)ret.get("avg"));
         dalClient.persist(dto);
    }


    private String getCreateDate() {
        Calendar now = Calendar.getInstance();
        if(now.get(Calendar.MONTH) < 3){
            return now.get(Calendar.YEAR)-2+"-12-31";
        }
        if(now.get(Calendar.MONTH) < 6){
            return now.get(Calendar.YEAR)-1+"-03-31";
        }
        if(now.get(Calendar.MONTH) < 9){
            return now.get(Calendar.YEAR)-1+"-06-30";
        }
        if(now.get(Calendar.MONTH) < 12){
            return now.get(Calendar.YEAR)-1+"-09-30";
        }
        return null;
    }
}
