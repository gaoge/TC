package com.feng.android.third_framework.okhttp;

import com.feng.android.third_framework.okhttp.interceptor.BridgeInterceptor;
import com.feng.android.third_framework.okhttp.interceptor.CacheInterceptor;
import com.feng.android.third_framework.okhttp.interceptor.CallServerInterceptor;
import com.feng.android.third_framework.okhttp.interceptor.Interceptor;
import com.feng.android.third_framework.okhttp.interceptor.RealInteceptorChain;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import timber.log.Timber;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-27 11:54
 * @tips
 */
public class RealCall implements Call{
    private final Request originalRequest;
    private final OkHttpClient client;

    public RealCall(Request request, OkHttpClient okHttpClient) {
        originalRequest = request;
        this.client = okHttpClient;
    }

    public static Call newCall(Request request, OkHttpClient okHttpClient) {
        return new RealCall(request,okHttpClient);
    }

    @Override
    public void enqueue(Callback callback) {
        //异步的
        AsyncCall asyncCall = new AsyncCall(callback);
        //交给线程池
        client.dispatcher.enqueue(asyncCall);
    }

    @Override
    public Response execute() {
        return null;
    }

    final class AsyncCall extends NamedRunnable{

        Callback callback;
        public AsyncCall(Callback callback) {
            this.callback = callback;
        }

        @Override
        protected void execute() {
            //来这里，开始访问网路 Reuquest -> Response
            Timber.e("RealCall execute");
            //Volley xUtils,Afinal AsyncHttpClient
            //基于 HttpURLConnection, OkHttp = Socket + okio(IO)
            final Request request = originalRequest;
            try{
                List<Interceptor> interceptors = new ArrayList<>();
                interceptors.add(new BridgeInterceptor());
                interceptors.add(new CacheInterceptor());
                interceptors.add(new CallServerInterceptor());
                Interceptor.Chain chain = new RealInteceptorChain(interceptors,0,originalRequest);
                Response response = chain.proceed(request);
                callback.onResponse(RealCall.this,response);

            }catch (IOException e){
                callback.onFailure(RealCall.this,e);
                e.printStackTrace();
            }
        }
    }
}
