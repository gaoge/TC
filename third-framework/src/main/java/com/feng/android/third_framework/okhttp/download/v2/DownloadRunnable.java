package com.feng.android.third_framework.okhttp.download.v2;

import com.feng.android.base.IO.IOUtils;
import com.feng.android.third_framework.okhttp.download.OkHttpManager;
import com.feng.android.third_framework.okhttp.download.v2.db.DaoManagerHelper;
import com.feng.android.third_framework.okhttp.download.v2.db.DownloadEntity;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.Response;
import timber.log.Timber;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-29 15:02
 * @tips
 * 负责 apk的部分 2-3MB
 */
public class DownloadRunnable implements Runnable{

    private static final int STATUS_DOWNLODING = 1 ;
    private static final int STATUS_STOP = 2 ;
    private final long start;
    private final long end;
    private int threadId;
    private String url;
    private DownloadCallback mDownloadCallback;
    private long mCurrentLenght;
    private int mStatus = STATUS_DOWNLODING;
    private long mProgress = 0;
    private DownloadEntity downloadEntity;

    public DownloadRunnable(String url, int threadId, long start, long end,long progress,DownloadEntity downloadEntity ,DownloadCallback callback) {
        this.threadId = threadId;
        this.url = url;
        this.start = start + progress;
        this.end = end;
        this.mDownloadCallback = callback;
        this.mProgress = progress;
        this.downloadEntity = downloadEntity;
    }

    @Override
    public void run() {
        //只读写我自己的内容，Range
        RandomAccessFile randomAccessFile = null;
        InputStream inputStream = null;
        try {
            Response response = OkHttpManager.getsManager().syncResponse(url,start,end);
            Timber.e(this.toString());

            inputStream = response.body().byteStream();
            //写数据
            File file = FileManager.manager().getFile(url);
            randomAccessFile = new RandomAccessFile(file,"rwd");
            //从这里开始
            randomAccessFile.seek(start);

            int len = 0;
            byte[] buffer = new byte[10 * 1024];

            while((len = inputStream.read(buffer)) != -1){
                if(mStatus == STATUS_STOP){
                    break;
                }
                //保存进度，做断点, 100kb

                randomAccessFile.write(buffer,0,len);
                mProgress += len;
                Timber.e(this.toString() + ",mProgress: " + mProgress);
            }


            mDownloadCallback.onSuccess(file);

        } catch (IOException e) {
            e.printStackTrace();
            mDownloadCallback.onFailure(e);
        }finally {
            IOUtils.close(inputStream);
            IOUtils.close(randomAccessFile);

            //存储到数据库，数据库怎么存
            downloadEntity.setProgress(mProgress);
            DaoManagerHelper.getManager().addEntity(downloadEntity);

        }
    }

    @Override
    public String toString() {
        return "DownloadRunnable{" +
                "start=" + start +
                ", end=" + end +
                ", threadId=" + threadId +
                ", url='" + url + '\'' +
                ", mDownloadCallback=" + mDownloadCallback +
                '}';
    }

    public void stop() {
        mStatus = STATUS_STOP;
    }
}
