package com.feng.android.third_framework.okhttp.download.v2;

import com.feng.android.third_framework.okhttp.download.OkHttpManager;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-29 15:02
 * @tips
 * 多任务，多线程，断点下载
 */
public class DownloadDispatcher {
    private static final DownloadDispatcher sDispather = new DownloadDispatcher();

    private DownloadDispatcher(){

    }

    public static DownloadDispatcher getsDispather() {
        return sDispather;
    }

    private final Deque<DownloadTask> readyTasks = new ArrayDeque<>();
    private final Deque<DownloadTask> runningTasks = new ArrayDeque<>();
    private final Deque<DownloadTask> stopTasks = new ArrayDeque<>();


    //最多只能下载4 5个
    public void startDownload(String url,DownloadCallback callback){
        
        Call call = OkHttpManager.getsManager().asyncCall(url);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //获取文件的大小
                long contentLength = response.body().contentLength();
                if(contentLength <= -1){
                    //没有获取到文件的大小
                    //1. 跟后台商量
                    //2. 只能采用单线程去下载
                    return;
                }
                //计算每个线程负责哪一块?
                DownloadTask downloadTask = new DownloadTask(url,contentLength,callback);
                downloadTask.init();

                runningTasks.add(downloadTask);

            }
        });
    }

    public void recyclerTask(DownloadTask downloadTask) {
        runningTasks.remove(downloadTask);

        //参考OkHttp 的Dispatcher的源码，如果还有需要下载的开始下一个的下载
    }

    public void stopDownload(String url){
        //这个停止的是不是正在运行的
    }

    //开个单独的线程去执行 所有下载的回调
}
