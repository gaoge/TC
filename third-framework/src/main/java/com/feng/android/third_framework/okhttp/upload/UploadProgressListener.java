package com.feng.android.third_framework.okhttp.upload;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-28 16:57
 * @tips
 */
public interface UploadProgressListener {

    void onProgress(long total,long current);
}
