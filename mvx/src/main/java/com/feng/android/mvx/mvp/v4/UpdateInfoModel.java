package com.feng.android.mvx.mvp.v4;

import com.feng.android.mvx.RetrofitClient;
import com.feng.android.net.entity.UpdateEntity;

import rx.Observable;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-06 10:43
 * @tips
 */
public class UpdateInfoModel implements UpdateInfoContract.UpdateInfoModel {
    @Override
    public Observable<UpdateEntity> getUpdateInfo(String token) {

        return RetrofitClient.getServiceApi()
                .update()
                .compose(RetrofitClient.transformer());
    }
}
