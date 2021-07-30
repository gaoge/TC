package com.feng.android.third_framework.okhttp.download;


import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-29 11:51
 * @tips
 */
public class OkHttpManager {

    private static final OkHttpManager sManager = new OkHttpManager();
    private OkHttpClient okHttpClient;

    private OkHttpManager(){
        okHttpClient = new OkHttpClient();
    }

    public static OkHttpManager getsManager() {
        return sManager;
    }

    public Call asyncCall(String url){
        Request request = new Request.Builder().url(url).build();
        return okHttpClient.newCall(request);

    }

    public Response syncResponse(String url, long start, long end) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Range","bytes=" + start + "-" + end)
                .build();
        return okHttpClient.newCall(request).execute();

    }
}
