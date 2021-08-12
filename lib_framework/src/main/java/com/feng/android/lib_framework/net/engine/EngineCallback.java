package com.feng.android.lib_framework.net.engine;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-04 14:29
 * @tips
 */
public interface EngineCallback {

    void onFailure(Exception e);
    //返回可以直接操作的对象，每次在上层自己用字符串去解析会蛋疼
    void onSuccess(String result);
}
