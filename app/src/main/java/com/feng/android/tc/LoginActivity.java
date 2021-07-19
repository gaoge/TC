package com.feng.android.tc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.feng.android.base.BaseActivity;
import com.feng.android.common.ui.navigationBar.DefaultNavigationBar;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-15 17:19
 * @tips
 */
public class LoginActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ViewGroup parent = (ViewGroup)findViewById(R.id.view_root);
//        NavigationBar navigationBar = new NavigationBar.Builder(this,R.layout.ui_navigation_bar,parent)
//                .setText(R.id.back_tv,"返回")
//                .setOnClickListener(R.id.back_tv,new View.OnClickListener(){
//                    @Override
//                    public void onClick(View v) {
//                        finish();
//                    }
//                })
//                .create();
//
//        //如果你想设置其它属性呢？比如文字大小，比如字体颜色，设置图片？等等
//        TextView textView = navigationBar.findViewById(R.id.back_tv);
//        textView.setVisibility(View.GONE);

        DefaultNavigationBar navigationBar = new DefaultNavigationBar.Builder(this,parent)
                .setLeftText("后退")
                .setLeftClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                })
                .hideLeftText()
                .create();


        //如果你想设置其它属性呢？比如文字大小，比如字体颜色，设置图片？等等
//        TextView textView = navigationBar.findViewById(R.id.back_tv);
//        textView.setVisibility(View.GONE);


        //还有就是有时候我们并不需要去关注id 以及我们头部的样式，大部分情况下是类似的，所以必须还要提供一个默认的

        setTitle("LoginActivity");
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

    public void click(View view) {
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }



}
