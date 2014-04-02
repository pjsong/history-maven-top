package site.netease;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import bean.NetEaseDTO;

public class WriterHistoryNeteaseHelper {
    
    static List<NetEaseDTO> step1PickUp(NetEaseDTO currentDTO, List<NetEaseDTO> list_desc, int step){
        List<NetEaseDTO> ret = new ArrayList<NetEaseDTO>();
        for(NetEaseDTO yd:list_desc){
            if(yd.getCode().equals(currentDTO.getCode()) && yd.getCreateDate().equals(currentDTO.getCreateDate())){
                int index = list_desc.indexOf(yd);
                if(list_desc.size() < (index+7*step)){
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
    static String step1Write(List<NetEaseDTO> list_desc){
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
    static ArrayList<Integer> breakPointIndex(List<NetEaseDTO> list_desc){
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
    

    
    /**之前包含自身：从所有的list中选择从code从当前日期开始(去处断点)之前的记录**/
    static List<NetEaseDTO> inDaysPickUpBefore(List<NetEaseDTO> list_desc, String code, String createDate){
        List<NetEaseDTO> ret = new ArrayList<NetEaseDTO>();
        for(int i = list_desc.size()-1;i>=0; i--){
            NetEaseDTO yd = list_desc.get(i);
            boolean currentRecordMatch = yd.getCode().equals(code) && yd.getCreateDate().equals(createDate);
            if(currentRecordMatch){
                ret.add(yd);
                break;
            }
            ret.add(yd);
        }
        return ret;
    }

    /**历史记录中x天内最高最低**/
    static String inDaysHighLowWrite(NetEaseDTO currentDTO, List<NetEaseDTO> list_history, String dbNo){
        int highIndex = 0, lowIndex = 0;
        //current highest, lowest
        double baseHigh = currentDTO.getPriceHigh();
        double baseLow = currentDTO.getPriceLow();
        boolean highContinued = true;
        boolean lowContinued = true;
        for(int i = 0; i <list_history.size(); i++){
            if(highContinued && list_history.get(i).getPriceHigh() < baseHigh){
                highIndex ++;
            }else{
                highContinued = false;
            }
            if(lowContinued && list_history.get(i).getPriceLow()>baseLow){
                lowIndex ++;
            }else{
                lowContinued = false;
            }
            if(!highContinued && !lowContinued){
                break;
            }
         }
        return highIndex+";"+lowIndex;
    }
    
    /**list_future is data of date after (not included) createDate
     * percentageStep is like 13,21,34,55,89,144,233,377
     * return days interval when reached target percentageStep
     * **/
    static int upDownWrite(NetEaseDTO currentDTO, List<NetEaseDTO> list_future, int percentageStep, boolean isUp){
        if(list_future.size() <= 1 || currentDTO.getPriceHigh() == 0 || currentDTO.getPriceLow() == 0){
            return 0;
        }
        double priceCurrent = (currentDTO.getPriceHigh() + currentDTO.getPriceLow())/2;
        if(priceCurrent == 0d){
            return 0;
        }
        for(int index = 1;index<list_future.size();index++){
            double priceIndexH = list_future.get(index).getPriceHigh();
            double priceIndexL = list_future.get(index).getPriceLow();
            double priceIndexM = (priceIndexH + priceIndexL)/2;
            //calculate down dates count
            if(!isUp && (priceIndexM/priceCurrent) < (1d - new Double(percentageStep) / 100)){
                return index;
            }
            if(isUp && (priceIndexM/priceCurrent) > (1d + new Double(percentageStep)/100)){
                return index;
            }
            if(index == 500){
                return 500;
            }
        }
        return 0;
    }
}
