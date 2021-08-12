package com.feng.android.lib_framework.net.engine.xutils;

import android.content.Context;

import com.feng.android.lib_framework.net.engine.EngineCallback;
import com.feng.android.lib_framework.net.engine.IHttpStrategy;
import com.feng.android.lib_framework.net.engine.SPHttpCache;

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
public class XUtilsStrategy implements IHttpStrategy {
    private SPHttpCache mHttpCache;

    public XUtilsStrategy(){
        mHttpCache = new SPHttpCache();
    }

    //参数还是很多
    public  void get(Context context, String url, Map<String,Object> params, final EngineCallback callBack, final boolean cache){
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
    public  void post(Context context, String url, Map<String, Object> params, EngineCallback callBack, boolean cache) {

    }

    @Override
    public  void download(Context context, String url, Map<String, Object> params, EngineCallback callBack) {

    }

    @Override
    public void upload(Context context, String url, Map<String, Object> params, EngineCallback callBack) {

    }
}
