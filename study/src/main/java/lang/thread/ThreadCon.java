package lang.thread;

import java.util.concurrent.TimeUnit;  

public class ThreadCon extends Thread  
{  
    public void run()  
    {  
         
        for(int i = 0; i < Integer.MAX_VALUE; i++)  
        {  
            System.out.println(i);  
            if(isInterrupted())  
            {  
                System.out.println("Interrupted...");  
                return;  
            }  
        }  
          
    }  
  
    public static void main(String[] args)  
    {  
        ThreadCon thread = new ThreadCon();  
        thread.start();  
        System.out.println("main thread");  
        try   
        {  
            TimeUnit.MILLISECONDS.sleep(200);  
        }   
        catch(InterruptedException e)   
        {  
            e.printStackTrace();  
        }  
        thread.interrupt();  
    }  
}  