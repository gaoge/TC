package com.feng.android.third_framework.okhttp.download.v2;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-29 17:33
 * @tips
 */
public abstract class DownloadProgressListener implements DownloadCallback{
    public abstract void progress(long current, long total);
}
