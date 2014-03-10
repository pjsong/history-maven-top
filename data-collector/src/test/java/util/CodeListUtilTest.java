package util;

import java.util.ArrayList;

import org.testng.annotations.Test;

public class CodeListUtilTest {
	@Test
	public void f() {
		ArrayList<String> arr = new ArrayList<String>();
		arr.add("a");
		arr.add("a");
		arr.add("a");
		arr.add("a");
		String ret = CodeListUtil.transCodeListToStr(arr);
		System.out.println(ret);
	}
}
