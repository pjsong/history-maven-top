package util;
import org.testng.annotations.Test;

import util.CalcUtil;


public class CalcUtilTest {
	
	@Test
	public void getFiboNacciTest(){
		for (int i = 0; i < 10; i++) {
			int fib = CalcUtil.getFiboNacci(i);
			int fib1 = CalcUtil.getFiboNacci(i+1);
			System.out.println( fib + " "
					+ fib1);
			int index = CalcUtil.getFibIndex(fib);
			System.out.println(index);
		}
	}
}
