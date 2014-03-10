package lang.thread;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class TimeoutFunction {
    // static final FutureTask<String> readLine = new FutureTask<String>(new Callable<String>() {
    // public String call() {
    // // long run job
    // for (int i = 0; i < 1000000; i++) {
    // System.out.println(i);
    // }
    // return "xx";
    // }
    // }) {
    // @Override
    // public boolean cancel(boolean mayInterruptIfRunning) {
    // return false;
    // }
    // };
    // static final FutureTask<String> timedReadLine = new FutureTask<String>(new Callable<String>() {
    // public String call() throws Exception {
    // try {
    // System.out.println("timedReadLine started");
    // return readLine.get(1, TimeUnit.MILLISECONDS);
    // } finally {
    // System.out.println("timedReadLine finally cancel started");
    // readLine.cancel(true);
    // }
    // }
    // });

    public static void main(String args[]) {
        ThreadTest d = new ThreadTest();
        d.start();
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println(d.finished);
        }
        d.interrupt();
        System.out.println(d.finished);
    }
}
class ThreadTest extends Thread{
    boolean finished = false;
    public void run() {
        for (int i = 0; i < 100000; i++) {
            System.out.println(i);
            if(isInterrupted())  
            {  
                System.out.println("Interrupted...");  
                return;  
            }  
        }      
        finished = true;
    }
    
}