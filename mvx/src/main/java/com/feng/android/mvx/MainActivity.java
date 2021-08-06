package com.feng.android.mvx;

import android.os.Bundle;

import com.feng.android.base.BaseActivity;
import com.feng.android.mvx.mvp.v1.MvpV1Activity;
import com.feng.android.mvx.mvp.v2.MvpV2Activity;
import com.feng.android.mvx.mvp.v3.MvpV3Activity;
import com.feng.android.mvx.mvp.v4.MvpV4Activity;
import com.feng.android.mvx.mvp.v5.MvpV5Activity;
import com.feng.android.mvx.mvp.v6.MvpV6Activity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(MvpV6Activity.class);
        finish();
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
}