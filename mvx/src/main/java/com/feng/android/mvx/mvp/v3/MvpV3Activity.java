package com.feng.android.mvx.mvp.v3;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.feng.android.base.mvp.BaseMVPActivity;
import com.feng.android.mvx.R;
import com.feng.android.lib_framework.net.entity.UpdateEntity;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-06 11:26
 * @tips
 */
public class MvpV3Activity extends BaseMVPActivity<UpdateInfoPresenter> implements UpdateInfoContract.UpdateView {

    //遗留问题： 多 Presenter怎么处理，dagger, 自己写Dagger处理

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUpdateInfo();
            }
        });
    }

    @Override
    protected void initTitle() {
        setTitle("MVP-V3");
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {


    }

    private void getUpdateInfo() {
        getPresenter().getUpdateInfo("");
    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onError(String error) {
        Toast.makeText(this,error,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onSucceed(UpdateEntity userInfo) {
        //成功 这个时候Activity有可能已经被关闭掉，有可能会异常崩溃(一般不会)
        //1. 可以判断界面是否还在
        //2. 解绑
        Toast.makeText(this,userInfo.toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    protected UpdateInfoPresenter createPresenter() {
        return new UpdateInfoPresenter();
    }

}
