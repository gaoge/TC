package com.feng.android.third_framework.okhttp.download.v2;

import com.feng.android.third_framework.okhttp.download.v2.db.DownloadEntity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import timber.log.Timber;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-29 15:03
 * @tips
 * 每一个apk的下载，这个类最终是要复用的，不复用也行
 */
public class DownloadTask {
    private String mUrl;
    private long mContentLength;
    private DownloadCallback mDownloadCallBack;


    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int THREAD_SIZE = Math.max(2,Math.min(CPU_COUNT-1,4));

    private List<DownloadRunnable> mRunnables;

    private ExecutorService executorService;
    private volatile int mSuccesedNumber;

    public synchronized ExecutorService executorService() {
        if (executorService == null) {
            executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 30, TimeUnit.SECONDS,new SynchronousQueue<Runnable>(),new ThreadFactory(){

                @Override
                public Thread newThread(Runnable r) {
                    Thread thread = new Thread(r,"DownloadTask");
                    thread.setDaemon(false);
                    return thread;
                }
            });
        }
        return executorService;
    }


    public DownloadTask(String url, long contentLength,DownloadCallback callback) {
        this.mDownloadCallBack = callback;
        this.mUrl = url;
        this.mContentLength = contentLength;
        mRunnables = new ArrayList<>();
    }

    public void init() {
        for(int i=0;i<THREAD_SIZE;i++){
            //计算出每个线程要下载的内容
            long threadSize = mContentLength/THREAD_SIZE;


            long start = threadSize * i;
            long end = (i + 1) * threadSize - 1;

            if(i == THREAD_SIZE -1){
                end = mContentLength - 1;
            }

            //初始化的时候，要去读取数据库
            List<DownloadEntity> entities = DaoManagerHelper.getManager().queryAll(mUrl);
            DownloadEntity downloadEntity = getEntity(i,entities);
            if(null == downloadEntity){
                downloadEntity = new DownloadEntity(start,end,mUrl,i,0,mContentLength);
            }

            DownloadRunnable downloadRunnable = new DownloadRunnable(mUrl,i,start,end,0,downloadEntity,new DownloadCallback(){

                @Override
                public void onFailure(IOException e) {
                    //一个apk 下载里面有一个线程异常了，处理异常,把其它线程停止掉
                    mDownloadCallBack.onFailure(e);
                }

                @Override
                public void onSuccess(File file) {
                    //线程同步一下，这里需要吗？
                    synchronized (DownloadTask.this){
                        Timber.e("mSuccesedNumber: " + mSuccesedNumber);
                        mSuccesedNumber += 1;
                        if(mSuccesedNumber == THREAD_SIZE){
                            mDownloadCallBack.onSuccess(file);
                        }
                        DownloadDispatcher.getsDispather().recyclerTask(DownloadTask.this);

                        //清楚数据库这个文件下载的存储
                    }

                }
            });
            //通过线程池去执行
            executorService().execute(downloadRunnable);
        }
    }

    //查询threadId对应的DownloadEntity
    private DownloadEntity getEntity(int threadId ,List<DownloadEntity> entities){
        for(DownloadEntity entity : entities){
            if(threadId == entity.getThreadId()){
                return entity;
            }
        }
        return null;
    }
    public void stop(){
        for(DownloadRunnable runnable : mRunnables){
            runnable.stop();
        }
    }
}
