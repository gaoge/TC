package com.feng.android.third_framework.okhttp;

import java.io.IOException;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-27 11:54
 * @tips
 */
public interface Callback {
    void onFailure(Call call, IOException e);
    void onResponse(Call call,Response response) throws IOException;
}
