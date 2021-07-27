package com.feng.android.third_framework.okhttp;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import okhttp3.internal.Util;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-27 12:54
 * @tips
 * 线程池处理
 */
public class Dispatcher {

    ExecutorService executorService;

    public synchronized ExecutorService executorService(){
        if(null == executorService){
            executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60,
                    TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    Thread thread = new Thread(r,"OkHttp");
                    thread.setDaemon(false);
                    return thread;
                }
            });
        }
        return executorService;
    }
    public void enqueue(RealCall.AsyncCall call) {
        executorService().execute(call);
    }
}
