package com.feng.android.third_framework.okhttp.interceptor;

import com.feng.android.third_framework.okhttp.RealCall;
import com.feng.android.third_framework.okhttp.Request;
import com.feng.android.third_framework.okhttp.RequestBody;
import com.feng.android.third_framework.okhttp.Response;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import timber.log.Timber;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-28 10:27
 * @tips
 */
public class CallServerInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Timber.e("intercept()");

        Request request = chain.request();
        URL url = new URL(request.url());
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


        urlConnection.connect();

        //写内容
        RequestBody requestBody = request.requestBody();
        if(null != requestBody){
            requestBody.onWriteBody(urlConnection.getOutputStream());
        }

        int statusCode = urlConnection.getResponseCode();
        if(statusCode == 200){
            InputStream inputStream = urlConnection.getInputStream();
            Response response = new Response(inputStream);
            return response;
        }

        return null;
    }
}
