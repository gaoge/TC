package com.feng.android.net.engine.retrofit;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-04 13:16
 * @tips
 */
public interface ServiceApi {
    //两个参数，一个是 url, 一个是 params
    @POST()
    Call<ResponseBody> postMethod(@Url String url, @FieldMap Map<String, Object> params);
    @GET()
    Call<ResponseBody> getMethod(@Url String url, @QueryMap Map<String, Object> params);
}
