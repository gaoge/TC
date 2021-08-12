package com.feng.android.mvx;



import com.feng.android.lib_framework.net.entity.Result;
import com.feng.android.lib_framework.net.entity.UpdateEntity;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-02 14:47
 * @tips
 */
public interface ServiceApi {


    @POST("v1/system/fixed/host/list")
    Observable<Result<List<String>>> systemList();

    @GET("update/api/v1/app/dev/4.3.00/29/huawei")
    Observable<Result<UpdateEntity>> update();

}
