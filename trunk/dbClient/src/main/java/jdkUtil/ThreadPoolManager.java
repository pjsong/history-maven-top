package jdkUtil;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池管理
 * 利用JDK1.5 的ThreadPoolExecutor来管理线程池
 * 
 * @author 
 *
 */

public class ThreadPoolManager {
    private  int coreSize = Runtime.getRuntime().availableProcessors();
    private  ThreadPoolExecutor taskPool = new ThreadPoolExecutor(   
                                          coreSize, coreSize * 4  , 30, TimeUnit.SECONDS,    
                                          new ArrayBlockingQueue<Runnable>(8),                                                 
                                          new ThreadPoolExecutor.CallerRunsPolicy()); 
    
    private static ThreadPoolManager instance = new ThreadPoolManager();        
    private ThreadPoolManager() {   
    } 

    public static ThreadPoolManager getInstance() { 
        return instance;
    } 
    
    public void run(Runnable command) { 
        this.taskPool.execute(command); 
    }   
}