package com.feng.android.base.IO;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-29 16:48
 * @tips
 */
public class IOUtils {

    public static void close(Closeable closeable) {
        if(null != closeable){
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
