package com.feng.android.net.engine;

import android.content.Context;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Map;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-12 09:29
 * @tips
 */
public class XUtilsRequest implements IHttpRequest {
    private SPHttpCache mHttpCache;

    public XUtilsRequest(){
        mHttpCache = new SPHttpCache();
    }

    //参数还是很多
    public <T> void get(Context context, String url, Map<String,Object> params, final HttpCallBack<T> callBack, final boolean cache){
        x.http().get(new RequestParams(), new Callback.CommonCallback<Object>() {
            @Override
            public void onSuccess(Object result) {

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    @Override
    public <T> void post(Context context, String url, Map<String, Object> params, HttpCallBack<T> callBack, boolean cache) {

    }

    @Override
    public <T> void download(Context context, String url, Map<String, Object> params, HttpCallBack<T> callBack) {

    }

    @Override
    public <T> void upload(Context context, String url, Map<String, Object> params, HttpCallBack<T> callBack) {

    }
}
