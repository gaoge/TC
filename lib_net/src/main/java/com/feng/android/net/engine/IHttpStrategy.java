package com.feng.android.net.engine;

import android.content.Context;

import java.util.Map;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-12 14:34
 * @tips
 */
public interface IHttpStrategy {
    //v1
//    <T> void get(Context context, String url, Map<String,Object> params, final HttpCallBack<T> callBack, final boolean cache);
//
//    <T> void post(Context context, String url, Map<String,Object> params, final HttpCallBack<T> callBack, final boolean cache);
//
//    <T> void download(Context context, String url, Map<String,Object> params, final HttpCallBack<T> callBack);
//
//    <T> void upload(Context context, String url, Map<String,Object> params, final HttpCallBack<T> callBack);

    //v2
    void get(Context context, String url, Map<String,Object> params, final EngineCallback callBack, final boolean cache);

    void post(Context context, String url, Map<String,Object> params, final EngineCallback callBack, final boolean cache);

    void download(Context context, String url, Map<String,Object> params, final EngineCallback callBack);

    void upload(Context context, String url, Map<String,Object> params, final EngineCallback callBack);
}
