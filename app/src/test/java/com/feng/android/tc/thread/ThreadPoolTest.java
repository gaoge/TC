package com.feng.android.tc.thread;

import org.junit.Test;
import org.junit.runners.JUnit4;
import org.junit.runners.model.InitializationError;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-19 15:20
 * @tips
 */
public class ThreadPoolTest {

    static ThreadPoolExecutor threadPoolExecutor;
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    //we want at least 2 threads and at most 4 threads in the core pool,
    //preferring to have 1 less than the CPU count to avoid saturating
    //the CPU with background work
    private static final int CORE_POOL_SIZE = Math.max(2,Math.min(CPU_COUNT-1,4));
    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;
    private static final int KEEP_ALIVE_SECONDS = 30;

    //Queue的参数
    //BlockingQueue: 先进先出的一个队列  FIFO
    //SynchronousQueue: 线程安全的队列，它里面是没有固定的缓存的(OkHttp所使用的)
    //PriorityBlockingQueue: 无序的，可以根据优先级进行排序

    private static final BlockingQueue<Runnable> sPoolWorkQueue = new LinkedBlockingDeque<>(128);

    //RejectedExecutionException 报错的原因，其实也是AsyncTask存在的一些隐患
    //线程队列大小是4，核心线程数也是4，最大线程数是10，目前待加入的Runnable有20个，
    //20个都要放到队列中，但是队列大小是4，所以还有16个没法放，这个时候最大线程数是10，非核心线程是6，
    //那么会拿6个出来执行，这个时候会重新创建6个线程，目前线程池就达10个线程，
    //但是还有10个Runnnable没办法存放，就只能抛异常了，意味着那10个Runnable没办法执行就会抛异常
    static {
        threadPoolExecutor = new ThreadPoolExecutor(
                4,//核心线程数
                10,//最大线程数
                30,//线程存活时间，没事干时候的最大闲置时间，闲置时间超过这个时间，该线程就会被销毁
                TimeUnit.SECONDS,//线程存活时间的单位
                sPoolWorkQueue,//线程队列
                new ThreadFactory() {//线程创建工厂,如果线程池需要创建线程，就会调用newThread来创建
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread =  new Thread(r,"线程自己的名字");
                        thread.setDaemon(false);// 不是守护线程
                        return new Thread(r);
                    }
                }
        );
    }

    @Test
    public void tmain() {
        System.out.println("tmain Start()");
        for(int i=0;i<8;i++){
//            System.out.println("i：" + i);

            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
//                        System.out.println("开始下载图片显示完毕" + Thread.currentThread().getName());

//                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("下载图片显示完毕" + Thread.currentThread().getName());
                }
            };
            //加入线程队列，寻找合适的时机去执行
            threadPoolExecutor.execute(runnable);
        }

        System.out.println("tmain end()");
    }

}
