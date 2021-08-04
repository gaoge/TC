package com.feng.android.net.engine;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.feng.android.net.ssl.TrustAllSslSocketFactory;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-12 09:29
 * @tips
 */
public class OKHttpRequest implements IHttpRequest {
    private SPHttpCache mHttpCache;

    public OKHttpRequest(){
        mHttpCache = new SPHttpCache();
    }

    //参数还是很多
    public <T> void get(Context context, String url, Map<String,Object> params, final HttpCallBack<T> callBack, final boolean cache){
        request(context, url, params, callBack, cache,"get");

    }

    private <T> void request(Context context, String url, Map<String, Object> params, HttpCallBack<T> callBack, boolean cache,String method) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .sslSocketFactory(TrustAllSslSocketFactory.getTrustAllSockeFactory(),TrustAllSslSocketFactory.getTrustManager())
                .build();
        //公共参数
        params.put("app_name","joke_essay");
        params.put("version_name","5.7.0");
        params.put("ac","wifi");
        params.put("device_id","30036228478");
        params.put("device_brand","xiaomi");

        final String jointUrl = Utils.jointParams(url, params);
//        //缓存问题
//        Log.d("Post请求路径",jointUrl);
//        final String cacheJson = mHttpCache.getCache(jointUrl);
//
//        //写一大堆内存处理逻辑，内存怎么扩展等等
//
//        if(cache && !TextUtils.isEmpty(cacheJson)){
//            Gson gson = new Gson();
//            T objResult = (T)gson.fromJson(cacheJson, Utils.analysisClazzInfo(callBack));
//            callBack.onSuccess(objResult);
//        }

        Request.Builder builder = new Request.Builder().url(jointUrl).tag(context);
        if(method == "post"){
            RequestBody requestBody = new RequestBody() {
                @Override
                public @Nullable MediaType contentType() {
                    return null;
                }

                @Override
                public void writeTo(@NotNull BufferedSink bufferedSink) throws IOException {

                }
            };
            builder = builder.post(requestBody);
        }
        //可以省略，默认是GET请求
        Request request = builder.build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onFailure(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String resultJson = response.body().string();

//                Log.d("TAG",resultJson.equals(cacheJson) + "");
//                if(cache && resultJson.equals(cacheJson)){
//                    return;
//                }
                //1. JSON解析转换
                //2. 显示列表数据
                //3. 缓存数据
                Gson gson = new Gson();
                //data : ["name","darren"] data:"请求失败
                T objResult = (T) gson.fromJson(resultJson, Utils.analysisClazzInfo(callBack));
                callBack.onSuccess(objResult);

                if(cache){
//                    PreferenceUtil.getInstance().saveParam(jointUrl,resultJson);
                    mHttpCache.saveCahce(jointUrl,resultJson);
                }

            }
        });
    }

    @Override
    public <T> void post(Context context, String url, Map<String, Object> params, HttpCallBack<T> callBack, boolean cache) {
        request(context, url, params, callBack, cache,"post");
    }

    @Override
    public <T> void download(Context context, String url, Map<String, Object> params, HttpCallBack<T> callBack) {

    }

    @Override
    public <T> void upload(Context context, String url, Map<String, Object> params, HttpCallBack<T> callBack) {

    }
}
