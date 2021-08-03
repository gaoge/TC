package com.feng.android.third_framework.together.v1;

import android.os.Bundle;

import com.feng.android.base.BaseActivity;
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
public class TogetherV1Activity extends BaseActivity {
    @Override
    protected void setContentView() {

    }

    @Override
    protected void initTitle() {
        setTitle("TogetherV1Activity");
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
            v1();
    }


    private void v1() {
        RetrofitClient.getServiceApi().systemList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Result<List<String>>>() {
                    @Override
                    public void onCompleted() {
                        Timber.e("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e("onCompleted");

                    }

                    @Override
                    public void onNext(Result<List<String>> listResult) {
                        Timber.e("onNext(): " + listResult.toString());

                    }
                });
    }
}
