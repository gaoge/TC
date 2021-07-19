package com.feng.android.base;
import android.content.Intent;
import android.os.Bundle;

import com.feng.android.base.manager.ActivityManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-19 14:33
 * @tips
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //1.设置布局
        setContentView();
        //写一些公用的方法，如ButterKinfe注解，统一管理Activity，等等
        ActivityManager.getInstance().attach(this);

        //2.初始化Title
        initTitle();
        //3.初始化View
        initView();
        //访问接口数据
        initData(savedInstanceState);
    }

    protected abstract void setContentView();
    protected abstract void initTitle();
    protected abstract void initView();
    protected abstract void initData(Bundle savedInstanceState);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.getInstance().detach(this);
    }

    //很多Activity都要用的
    public void startActivity(Class<? extends BaseActivity> clazz){
        Intent intent = new Intent(this,clazz);
        startActivity(intent);
    }
}
