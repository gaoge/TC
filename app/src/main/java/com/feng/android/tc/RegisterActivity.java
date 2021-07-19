package com.feng.android.tc;

import android.os.Bundle;
import android.view.View;

import com.feng.android.base.BaseActivity;
import com.feng.android.base.manager.ActivityManager;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-15 17:19
 * @tips
 */
public class RegisterActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);
        setTitle("RegisterActivity");


    }

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

    }

    public void click(View view){
        //不光要关闭自己，还要关闭LoginActivity 让其返回到主页
        ActivityManager.getInstance().finish(this);
        ActivityManager.getInstance().finish(LoginActivity.class);
    }


}
