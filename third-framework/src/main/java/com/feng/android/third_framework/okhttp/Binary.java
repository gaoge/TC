package com.feng.android.third_framework.okhttp;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-27 15:15
 * @tips
 */
public interface Binary {
    long fileLength();

    String mimType();

    String fileName();

    void onWrite(OutputStream outputStream) throws IOException;
}
