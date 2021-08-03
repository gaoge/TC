package com.feng.android.third_framework.retrofit.my;



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
    Call<UserLoginResult> userLogin(@Query("userName") String username, @Query("pwd") String pwd);


}
