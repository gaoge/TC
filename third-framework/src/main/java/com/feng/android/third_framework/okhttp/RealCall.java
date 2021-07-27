package com.feng.android.third_framework.okhttp;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

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

                URL url = new URL(request.url);
                HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
                if(urlConnection instanceof HttpsURLConnection){
                    //https的一些操作
                    //httpsURLConnection.setHostnameVerifier();
                    //httpsURLConnection.setSSLSocketFactory();
                }

//                urlConnection.setReadTimeout();
                //写东西
                urlConnection.setRequestMethod(request.method.name);
                urlConnection.setDoOutput(request.method.doOutput());
                RequestBody requestBody = request.requestBody;
                if(null != requestBody){
                    //头信息
                    urlConnection.setRequestProperty("Content-Type",requestBody.getContentType());
                    urlConnection.setRequestProperty("Content-Length",Long.toString(requestBody.getContentLength()));

                }

                //写内容
                if(null != requestBody){
                    requestBody.onWriteBody(urlConnection.getOutputStream());
                }
                urlConnection.connect();

                //写

                int statusCode = urlConnection.getResponseCode();
                if(statusCode == 200){
                    InputStream inputStream = urlConnection.getInputStream();
                    Response response = new Response(inputStream);
                    callback.onResponse(RealCall.this,response);
                }

                //进行一系列操作 状态码
            }catch (IOException e){
                callback.onFailure(RealCall.this,e);
                e.printStackTrace();
            }
        }
    }
}
