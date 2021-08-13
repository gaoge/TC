package com.feng.android.home;

import android.os.Bundle;
import android.widget.Toast;

import com.feng.android.lib_framework.skin.BaseSkinActivity;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-12 17:16
 * @tips
 */
public  class TestActivity extends BaseSkinActivity {
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
//        int i = 2/0;
        Toast.makeText(this,2/0 + "bug修复成功",Toast.LENGTH_SHORT).show();
    }
}
