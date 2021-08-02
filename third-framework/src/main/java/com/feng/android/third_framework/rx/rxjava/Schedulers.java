package com.feng.android.third_framework.rx.rxjava;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;


/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-02 10:00
 * @tips
 */
public abstract class Schedulers {

    static Schedulers IO;
    static Schedulers MAIN_THREAD;

    static {
        //
        IO = new IOSchedulers();
        MAIN_THREAD = new MainSchedulers(new Handler(Looper.getMainLooper()));

    }

    public static Schedulers io() {
        return IO;
    }

    public static Schedulers mainThread() {
        return MAIN_THREAD;
    }

    public <T> void scheduleDirect(Runnable runnable) {
    }

    private static class IOSchedulers extends Schedulers {
        ExecutorService service;

        public IOSchedulers(){
            service = Executors.newScheduledThreadPool(1, new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    return new Thread(r);
                }
            });
        }
        @Override
        public <T> void scheduleDirect(Runnable runnable) {
            super.scheduleDirect(runnable);
            service.submit(runnable);
        }
    }

    private static class MainSchedulers extends Schedulers {
        Handler handler;
        public MainSchedulers(Handler handler) {
            this.handler = handler;
        }

        @Override
        public void scheduleDirect(Runnable runnable) {
            Message message = Message.obtain(handler,runnable);
            //这里因为给message传递了一个runnable,所以不用再调用Handler的handleMessage方法
            handler.sendMessage(message);
        }
    }
}
