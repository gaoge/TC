package com.feng.android.third_framework.retrofit.my;

import android.os.Bundle;

import com.feng.android.base.BaseActivity;

import timber.log.Timber;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-02 17:50
 * @tips
 */
public class MyRetrofiActivity extends BaseActivity {
    @Override
    protected void setContentView() {

    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        RetrofitClient.getServiceApi().userLogin("gaoge","123456")
                .enqueue(new Callback<UserLoginResult>() {
                    @Override
                    public void onResponse(Call<UserLoginResult> call, Response<UserLoginResult> response) {
                        Timber.e(response.body.toString());
                    }

                    @Override
                    public void onFailure(Call<UserLoginResult> call, Throwable t) {

                    }
                });
    }
}
