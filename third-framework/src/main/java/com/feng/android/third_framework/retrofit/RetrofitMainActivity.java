package com.feng.android.third_framework.retrofit;

import android.os.Bundle;
import android.widget.Toast;

import com.feng.android.base.BaseActivity;
import com.feng.android.third_framework.retrofit.v1.UserInfo;
import com.feng.android.third_framework.retrofit.v3.HttpCallback;
import com.feng.android.third_framework.retrofit.v2.Result;
import com.feng.android.third_framework.retrofit.v2.UserLoginResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-02 15:09
 * @tips
 */
public class RetrofitMainActivity extends BaseActivity {
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
//        v1();
//        v2();
        v3();

    }



    private void v2() {
        RetrofitClient.getServiceApi().userLoginV2("gaoge","123456")
                .enqueue(new Callback<Result<UserInfo>>() {
                    @Override
                    public void onResponse(Call<Result<UserInfo>> call, Response<Result<UserInfo>> response) {
                        Result<UserInfo> result = response.body();
                        if(result.isOk()){

                        }else{

                        }
                    }

                    @Override
                    public void onFailure(Call<Result<UserInfo>> call, Throwable t) {

                    }
                });
    }

    private void v3() {
        RetrofitClient.getServiceApi().userLoginV2("gaoge","123456")
                .enqueue(new HttpCallback<UserInfo>() {
                    @Override
                    public void onSucceed(UserInfo result) {
                        //成功
                        Toast.makeText(RetrofitMainActivity.this,"成功: " + result.toString(),Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onError(String code, String msg) {
                        //失败
                        Toast.makeText(RetrofitMainActivity.this,msg,Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void v1() {
        Call<UserLoginResult> call = RetrofitClient.getServiceApi().userLoginV1("gaoge", "123456");

        call.enqueue(new Callback<UserLoginResult>() {
            @Override
            public void onResponse(Call<UserLoginResult> call, Response<UserLoginResult> response) {
                //
                UserLoginResult loginResult = response.body();
                Timber.e(loginResult.data.toString());
                if(loginResult.isOk()){
                    //解析

                }else{
                    Toast.makeText(RetrofitMainActivity.this,loginResult.getMsg(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserLoginResult> call, Throwable t) {
                Timber.e(t.getMessage());
            }
        });
    }
}
