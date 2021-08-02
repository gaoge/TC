package com.feng.android.third_framework.rx.RxLogin;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.feng.android.base.BaseActivity;
import com.feng.android.butterknife.Butterknife;
import com.feng.android.butterknife.Unbinder;
import com.feng.android.butterknife_annotations.BindView;
import com.feng.android.third_framework.R;
import com.jakewharton.rxbinding4.view.RxView;

import io.reactivex.rxjava3.functions.Consumer;
import kotlin.Unit;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-02 11:14
 * @tips
 */
public class RxLoginMainActivity extends BaseActivity {

    @BindView(R.id.username)
    EditText username;

    @BindView(R.id.pwd)
    EditText pwd;

    @BindView(R.id.login)
    Button login;

    @BindView(R.id.clear)
    ImageView clear;

    Unbinder bind;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void initView() {

        bind = Butterknife.bind(this);

        login.setEnabled(true);

        RxView.clicks(login).subscribe(new Consumer<Unit>() {
            @Override
            public void accept(Unit unit) throws Throwable {
                RxLogin.create(RxLoginMainActivity.this)
                        .doOauthVerifty(RxLoginPlatform.PLATFORM_QQ)
                        .subscribe(new Consumer<RxLoginResult>() {
                            @Override
                            public void accept(RxLoginResult rxLoginResult) throws Throwable {
                                if(rxLoginResult.isSucceed()){

                                }
                            }
                        });

            }
        });


    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
