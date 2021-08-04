package com.feng.android.net.engine;

import android.content.Context;

import java.util.Map;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-12 14:34
 * @tips
 */
public interface IHttpRequest {
    <T> void get(Context context, String url, Map<String,Object> params, final HttpCallBack<T> callBack, final boolean cache);

    <T> void post(Context context, String url, Map<String,Object> params, final HttpCallBack<T> callBack, final boolean cache);

    <T> void download(Context context, String url, Map<String,Object> params, final HttpCallBack<T> callBack);

    <T> void upload(Context context, String url, Map<String,Object> params, final HttpCallBack<T> callBack);
}
