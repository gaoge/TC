package com.feng.android.third_framework.rx.rxbinding;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.feng.android.base.BaseActivity;
import com.feng.android.butterknife.Butterknife;
import com.feng.android.butterknife.Unbinder;
import com.feng.android.butterknife_annotations.BindView;
import com.feng.android.third_framework.R;
import com.jakewharton.rxbinding4.InitialValueObservable;
import com.jakewharton.rxbinding4.view.RxView;
import com.jakewharton.rxbinding4.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Consumer;
import kotlin.Unit;
import timber.log.Timber;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-02 11:14
 * @tips
 */
public class RxBindingMainActivity extends BaseActivity {

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
//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(RxBindingMainActivity.this,"login click",Toast.LENGTH_SHORT).show();
//            }
//        });

        //EditText 输入事件监听
        RxTextView.textChanges(username).subscribe(new Consumer<CharSequence>() {
            @Override
            public void accept(CharSequence charSequence) throws Throwable {
                clear.setVisibility(TextUtils.isEmpty(charSequence) ? View.INVISIBLE : View.VISIBLE);
            }
        });

        Observable<CharSequence> userNameObservable = RxTextView.textChanges(username);
        Observable<CharSequence> pwdObservable = RxTextView.textChanges(pwd);

        //TODO :暂未生效
        //联合用户名输入框，密码输入框，控制登录按钮状态
        Observable.combineLatest(userNameObservable, pwdObservable, new BiFunction<CharSequence, CharSequence, Object>() {
            @Override
            public Object apply(CharSequence charSequence, CharSequence charSequence2) throws Throwable {
                if(!TextUtils.isEmpty(charSequence) && !TextUtils.isEmpty(charSequence2)){
                    login.setEnabled(true);
                }else{
                    login.setEnabled(false);
                }
                return null;
            }
        });

        //防止按钮重复点击
        RxView.clicks(clear).debounce(300, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Unit>() {
                    @Override
                    public void accept(Unit unit) throws Throwable {
                        Timber.e("clear button click");
                    }
        });

        //延时操作
        RxTextView.textChanges(username).debounce(1200,TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<CharSequence>() {
                    @Override
                    public void accept(CharSequence charSequence) throws Throwable {
                        Timber.e("发起查询请求");
                    }
        });

        //接口轮循
//        Observable.interval(2,2,TimeUnit.SECONDS)
//                .subscribe(new Consumer<Long>() {
//                    @Override
//                    public void accept(Long aLong) throws Throwable {
//                        Timber.e("轮询操作");
//                    }
//                });

        //延时操作,启动页延时
        Observable.timer(2,TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Throwable {
                        Timber.e("延时操作");
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
