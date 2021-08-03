package com.feng.android.third_framework.together.v2;

import android.os.Bundle;
import android.view.View;

import com.feng.android.base.BaseActivity;
import com.feng.android.third_framework.R;
import com.feng.android.third_framework.retrofit.RetrofitClient;
import com.feng.android.third_framework.retrofit.v1.UserInfo;
import com.feng.android.third_framework.retrofit.v2.Result;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-03 14:13
 * @tips
 */
public class TogetherV2Activity extends BaseActivity {
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initTitle() {
        setTitle("TogetherV2");
    }

    @Override
    protected void initView() {
        findViewById(R.id.txt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v2();
            }
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    private void v2() {
        RetrofitClient.getServiceApi()
                .systemList()
                .compose(RetrofitClient.transformer())
                .subscribe(new BaseSubscriber<List<String>>() {
                    @Override
                    protected void onFailure(String errorCode, String errorMessage) {
                        Timber.e("[onFailure]: " + errorMessage);
                    }

                    @Override
                    protected void onSuccess(List<String> strings) {
                        Timber.e("[onSuccess]: " +strings.toString());
                    }

                });
    }

}
