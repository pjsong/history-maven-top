package util;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.testng.annotations.Test;

import util.CalcUtil;

public class CalcUtilTest {
    @Test
    public void getFiboNacciTest() {
        Calendar gc = CalcUtil.getFibCalendar(Calendar.getInstance(), 1);
        for (int i = 1; i < 10; i++) {
            String year = CalcUtil.getFibCalendar(Calendar.getInstance(), i).get(Calendar.YEAR)+"";
            String month = CalcUtil.getFibCalendar(Calendar.getInstance(), i).get(Calendar.MONTH)+1+"";
            System.out.println(year+" "+month+" "+CalcUtil.getFibCalendar(Calendar.getInstance(), i).get(Calendar.DAY_OF_MONTH));
        }
    }
    // @Test
    // public void getFiboNacciTest(){
    // for (int i = 0; i < 10; i++) {
    // int fib = CalcUtil.getFiboNacci(i);
    // int fib1 = CalcUtil.getFiboNacci(i+1);
    // System.out.println( fib + " "
    // + fib1);
    // int index = CalcUtil.getFibIndex(fib);
    // System.out.println(index);
    // }
    // }
}
