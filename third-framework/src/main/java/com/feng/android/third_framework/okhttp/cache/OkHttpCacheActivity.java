package com.feng.android.third_framework.okhttp.cache;

import android.os.Bundle;
import android.os.Environment;

import com.feng.android.base.BaseActivity;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import timber.log.Timber;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-28 17:25
 * @tips
 */
public class OkHttpCacheActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        cache();


    }

    private void cache() {
        //自定义缓存（要求: 30s内请求读缓存，无网直接读缓存)
        //OKHttp自带的扩展有坑，我们之前自己写过这个缓存管理，与OkHttp结合就可以了

        //思路？ 拦截器？ 分为两种

        String url = "https://www.baidu.com";
        //构建一个请求
        File file = new File(Environment.getExternalStorageDirectory(),"cache");
        Cache cache = new Cache(file,10 * 1024 * 1024);

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .cache(cache)
                //加载最前
                .addInterceptor(new CacheRequestInteceptor(this))
                //加载最后,数据缓存 过期时间 30s
                .addNetworkInterceptor(new CacheResponseInteceptor())
                .build();

        // 构建一个请求
        final Request request = new Request.Builder()
                .url(url)
                .build();
        // new RealCall 发起请求
        Call call = httpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Timber.e(response.cacheResponse() + ";" + response.networkResponse());
            }
        });
    }

    @Override
    protected void setContentView() {

    }

    @Override
    protected void initTitle() {
        setTitle("CacheActivity");

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }
}
