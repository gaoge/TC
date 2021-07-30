package com.feng.android.third_framework.okhttp.download.v1;

import android.content.Context;

import com.feng.android.base.platform.PlatformUtil;
import com.feng.android.third_framework.okhttp.download.OkHttpManager;
import com.feng.android.third_framework.okhttp.download.v2.DownloadProgressListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import timber.log.Timber;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-29 17:11
 * @tips
 * 单线程下载
 */
public class DownloadManager {


    private static DownloadManager sManager = new DownloadManager();

    private DownloadManager(){

    }

    public static DownloadManager getsManager() {
        return sManager;
    }

    private long mCurrentLenght;
    private long mTotalLength;

    private DownloadProgressListener progressListener;

    public void download(Context context,String url,DownloadProgressListener listener){
        //多线程断点下载，只需客户端做处理就可以
        //什么叫断点续传，逻辑是什么？
        //如果下载中断（网络断开，程序退出），下次可以接着上次的地方下载
        //多线程的逻辑是什么？多个线程读后台文件每个线程只负责读取单独的内容

        //文件更新，专门下载apk软件(应用宝，迅雷，百度云)

        //文件更新 ，1：可以直接跳转到浏览器更新  2：直接下载不断点  3：多线程  4： 多线程加断点

        //专门下载apk软件 ：多线程 + 断点，最多只能同时下载几个文件，一些暂停，一些准备，参考okhttp 源码 Dispatcher的逻辑

        //4. 多线程加断点


        File file = new File(context.getCacheDir(),"凤凰金融.apk");


        long startDownloadTime = System.currentTimeMillis();

        OkHttpManager okHttpManager = OkHttpManager.getsManager();
        Call call = okHttpManager.asyncCall(url);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //不断的读写文件，单线程
                InputStream inputStream = response.body().byteStream();
                mTotalLength = response.body().contentLength();


                OutputStream outputStream = new FileOutputStream(file);

                int len = 0;
                byte[] buffer = new byte[10 * 1024];

                while((len = inputStream.read(buffer)) != -1){
                    outputStream.write(buffer,0,len);
                    mCurrentLenght += len;
                    listener.progress(mCurrentLenght,mTotalLength);
                }

                inputStream.close();
                outputStream.close();

                long endDownloadTime = System.currentTimeMillis();
                long downloadTime = (endDownloadTime - startDownloadTime) / 1000;
                Timber.e("download take : " + downloadTime + "s");
                listener.onSuccess(file);

            }
        });


        //断点续传，需要服务器配合，思路跟断点下载类似
    }

    private boolean needDownload(File file) {
        if(null != file && file.exists()){
            return false;
        }
        return true;
    }


}
