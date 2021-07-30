package com.feng.android.third_framework.okhttp.download.v2;

import java.io.File;
import java.io.IOException;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-29 15:04
 * @tips
 */
public interface DownloadCallback {
    void onFailure(IOException e);

    void onSuccess(File file);

}
