package com.feng.android.third_framework.okhttp.cache;

import android.content.Context;

import com.feng.android.net.NetUtil;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-28 17:37
 * @tips
 */
public class CacheRequestInteceptor implements Interceptor {

    private Context mContext;

    public CacheRequestInteceptor(Context context) {
        this.mContext = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if(!NetUtil.networkAvailable(mContext)){
            //只读缓存
            request = request.newBuilder()
//                    .cacheControl(new CacheControl.Builder().onlyIfCached().build())
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();

        }
        return chain.proceed(request);
    }
}
