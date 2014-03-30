package site.yahoo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import bean.YahooDTO;

public class WriterHistoryYahooHelper {
    
    static List<YahooDTO> step1PickUp(List<YahooDTO> list_desc, String code, String createDate, int step, ArrayList<Integer> breakPointArr){
        List<YahooDTO> ret = new ArrayList<YahooDTO>();
        for(YahooDTO yd:list_desc){
            if(yd.getCode().equals(code) && yd.getCreateDate().equals(createDate)){
                int index = list_desc.indexOf(yd);
                if(list_desc.size() < (index+7*step)){
                    break;
                }
                if(breakPointArr.contains(new Integer(list_desc.indexOf(yd)))){
                    break;
                }
                ret.add(yd);
                ret.add(list_desc.get(index+1*step));
                ret.add(list_desc.get(index+2*step));
                ret.add(list_desc.get(index+3*step));
                ret.add(list_desc.get(index+4*step));
                ret.add(list_desc.get(index+5*step));
                ret.add(list_desc.get(index+6*step));
                break;
            }
        }
        return ret;
    }
    /**对size为7的list，以最后一个数为分母，其余的分别为分子，减去1后的百分数；
     * 以分号隔开最高最低**/
    static String step1Write(List<YahooDTO> list_desc){
        if(list_desc.size() != 7 || list_desc.get(6).getPriceHigh() == 0d || list_desc.get(6).getPriceLow() == 0d){
            return "";
        }
        StringBuffer retStr = new StringBuffer();
        double basePrice = list_desc.get(0).getPriceHigh();
        for(int i=6;i>=0;i--){
            double ratio = (list_desc.get(i).getPriceHigh()/basePrice -1d)*100;
            retStr.append(ratio+",");
        }
        retStr.deleteCharAt(retStr.lastIndexOf(","));
        
        StringBuffer retStrLow = new StringBuffer();
        double basePriceLow = list_desc.get(0).getPriceLow();
        for(int i=0;i<6;i++){
            double ratio = (list_desc.get(i).getPriceLow()/basePriceLow -1d)*100;
            retStrLow.append(ratio+",");
        }
        retStrLow.deleteCharAt(retStrLow.lastIndexOf(","));
        
        return retStr.toString()+";"+retStrLow;
    }
    
    /**each element in the returned list is a start point for calc**/
    static ArrayList<Integer> breakPointIndex(List<YahooDTO> list_desc){
        ArrayList<Integer> ret = new ArrayList<Integer>();
        for(int index = 0;index<list_desc.size() -1 ;index++){
            double priceIndex = list_desc.get(index).getPriceHigh();
            double priceIndexNext = list_desc.get(index+1).getPriceHigh();
            if(priceIndex/priceIndexNext < 0.89 || priceIndex/priceIndexNext > 1.11){
                ret.add(index);
            }
        }
        return ret;
    }
    
    /**之后（包含自身）：从所有的list中选择从code从当前日期开始(去处断点)之后的记录**/
    static List<YahooDTO> upDownPickUp(List<YahooDTO> list_desc, String code, String createDate, ArrayList<Integer> breakPointArr){
        List<YahooDTO> ret = new ArrayList<YahooDTO>();
        boolean indexSelected = false;
        for(int i=list_desc.size()-1 ;i >= 0;i--){
            YahooDTO yd = list_desc.get(i);
            if(indexSelected || (yd.getCode().equals(code) && yd.getCreateDate().equals(createDate))){
                if(!indexSelected){
                    indexSelected = true;
                }
            }
            if(!indexSelected){
                continue;
            }
            if(breakPointArr.contains(new Integer(list_desc.indexOf(yd)))){
                break;
            }
            ret.add(yd);
        }
        return ret;
    }
    /**之前包含自身：从所有的list中选择从code从当前日期开始(去处断点)之前的记录**/
    static List<YahooDTO> inDaysPickUpBefore(List<YahooDTO> list_desc, String code, String createDate, ArrayList<Integer> breakPointArr){
        List<YahooDTO> ret = new ArrayList<YahooDTO>();
        for(int i = list_desc.size()-1;i>=0; i--){
            YahooDTO yd = list_desc.get(i);
            if(breakPointArr.contains(new Integer(list_desc.indexOf(yd)))){
                ret.clear();
                continue;
            }
            boolean currentRecordMatch = yd.getCode().equals(code) && yd.getCreateDate().equals(createDate);
            if(currentRecordMatch){
                ret.add(yd);
                break;
            }
            ret.add(yd);
        }
        return ret;
    }

    /**当前日期最高价覆盖范围，最低价覆盖范围**/
    static String inDaysHighLowWrite(List<YahooDTO> list_desc, String code, String createDate, ArrayList<Integer> breakPointArr, String dbNo){
        List<YahooDTO> selectedList = WriterHistoryYahooHelper.inDaysPickUpBefore(list_desc, code, createDate, breakPointArr);
        if(selectedList == null || selectedList.size() < 2){
            return "0;0";
        }
        int highIndex = 0, lowIndex = 0;
        String highIndexStr = "";
        String lowIndexStr = "";
        //current highest, lowest
        double baseHigh = selectedList.get(selectedList.size()-1).getPriceHigh();
        double baseLow = selectedList.get(selectedList.size()-1).getPriceLow();
        //count from current
        for(int i = selectedList.size() - 2; i >= 0; i--){
            if(StringUtils.isBlank(highIndexStr) && selectedList.get(i).getPriceHigh()<baseHigh){
                highIndex++;
            }else{
                highIndexStr = ""+highIndex;
            }
            if(StringUtils.isBlank(lowIndexStr) && selectedList.get(i).getPriceLow()>baseLow){
                lowIndex ++;
            }else{
                lowIndexStr = ""+lowIndex;
            }
            if(StringUtils.isNotBlank(lowIndexStr) && StringUtils.isNotBlank(highIndexStr)){
                break;
            }
        }
        return highIndex+";"+lowIndex;
    }
    static int upDownWrite(List<YahooDTO> list_desc, int percentageStep, boolean isUp){
        if(list_desc.size() <= 1){
            return 0;
        }
        double priceBase = list_desc.get(0).getPriceHigh();
        for(int index = 1;index<list_desc.size();index++){
            double priceIndexP = list_desc.get(index).getPriceHigh();
            if(!isUp && priceIndexP/priceBase < (1d - new Double(percentageStep) / 100)){
                return index;
            }
            if(isUp && priceIndexP/priceBase > (1d + new Double(percentageStep)/100)){
                return index;
            }
        }
        return 0;
    }
}
