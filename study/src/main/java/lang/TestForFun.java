package lang;

import java.io.IOException;
import java.util.Arrays;

public class TestForFun {
    public static void test1() throws IOException{
        int[] x = new int[6];  
        Arrays.fill(x, 1);  
        for (int i = 0; i < x.length; i++) {  
            System.in.read();  
            System.out.println(x[i]);  
        }  
    }  
    /*in debug it's recursive loop, in run mode finaly will invoked before exception*/
    private static void test2() {  
        try {  
            System.out.println("try");  
            test2();  
        } catch (Throwable e) {  
            System.out.println("catch");  
            test2();  
        } finally {  
            System.out.println("finally");  
            test2();  
        }  
    }  
    public static void main(String[] args) throws Exception {  
        test2();
    }
}
