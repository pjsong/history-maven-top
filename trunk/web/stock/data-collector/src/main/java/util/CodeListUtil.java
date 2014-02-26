package util;

import java.util.ArrayList;
import java.util.List;

public class CodeListUtil {
	public static String transCodeListToStr(List codeList){
		if(codeList == null || codeList.size() == 0){
			return "";
		}
		String strOfArr = codeList.toString();
		//去首尾，空格
		strOfArr = strOfArr.substring(1, strOfArr.length()-1);
		return strOfArr.replace(" ", "");
	}
	/**000001.sz to sz000001**/
	public static String transYahooToLocalCode(String yahooCode){
	    String str[] = yahooCode.split(".");
	    return str[1]+str[0];
	}

    /**转换sz002121到002121.sz**/
    public static String transLocalToYahooCode(String yahooCode){
        if(yahooCode == null || yahooCode.length()<=2){
            return null;
        }
        return yahooCode.substring(2)+"."+yahooCode.substring(0, 2);
    }
    /**转换sz002121到002121**/
    public static String transLocalToSohuCode(String sohuCode){
        if(sohuCode == null || sohuCode.length()<=2){
            return null;
        }
        return sohuCode.substring(2);
    }
}
