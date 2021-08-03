package com.feng.android.third_framework.retrofit;

import com.feng.android.third_framework.retrofit.v1.UserInfo;
import com.feng.android.third_framework.retrofit.v2.Result;
import com.feng.android.third_framework.retrofit.v2.UserLoginResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-02 14:47
 * @tips
 */
public interface ServiceApi {

    //接口涉及到解耦，userLogin 方法是没有任何实现的
    //如果有一天要换 GoogleHttp
    @GET("LoginServlet")
    Call<UserLoginResult> userLoginV1(@Query("userName") String username, @Query("pwd") String pwd);

    @GET("LoginServlet")
    Call<Result<UserInfo>> userLoginV2(@Query("userName") String username, @Query("pwd") String pwd);
}
