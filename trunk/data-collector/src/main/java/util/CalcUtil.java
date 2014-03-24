
package util;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author Administrator
 * 用于各类函数计算
 * 
 * 所有x天内涨跌y的z日曲线，在U天内涨跌V的次数。
 * 	 * 表列1：日期，列2：价格-2%...
	 * 涨跌数序列[]：+-1%， +-2%,+-3%,+-5%,+-8%,+-13%,...天数值。
	 * 天数序列[]：+-1天， +-2天,...涨跌值。 
	 * 特征值分割长度序列
 */
public class CalcUtil {
	
	/**

	 * @param index 0,1,2,3,4
	 * @return fibonacci序列，1,2,3,5,8,13,21,34,...
	 */
	private static int getFiboNacci(int index){
		index = index+1;
		double factor1 = 1/Math.sqrt(5d);
		double term1 = Math.pow((1+Math.sqrt(5))/2, new Double(index));
		double term2 = Math.pow((1-Math.sqrt(5))/2, new Double(index));
		return new Double(factor1*(term1 - term2)).intValue();
	}

	/**
	 * @param fibValue, 1,1,2,3,
	 * @return 0,0,1,2
	 */
	private static int getFibIndex(int fibValue){
		//极限Math.log(Math.sqrt(5)-1)/2
        double limit = (Math.sqrt(5)-1)/2;
        double logupper = Math.log(1d/fibValue);
        double loglower = Math.log(limit);
		BigDecimal bd = new BigDecimal(logupper/loglower);
		return bd.setScale(0, 4).intValue();
	}
	
	/**
	 * add date from calendar
	 * **/
	public static Calendar getFibCalendar(Calendar calendar, int fibIndex){
	    calendar.add(Calendar.DAY_OF_MONTH, getFiboNacci(fibIndex));
	    return calendar;
	}
	
	
	public static void main(String[] args){
		for (int i = 1; i < 10; i++) {
			int fib = getFiboNacci(i);
			System.out.println(fib);
			int index = CalcUtil.getFibIndex(fib);
			System.out.println(index);

		}
	}
}
