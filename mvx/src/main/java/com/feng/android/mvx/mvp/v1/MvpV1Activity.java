package com.feng.android.mvx.mvp.v1;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.feng.android.base.BaseActivity;
import com.feng.android.mvx.R;
import com.feng.android.net.entity.UpdateEntity;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-06 11:26
 * @tips
 */
public class MvpV1Activity extends BaseActivity implements UpdateInfoContract.UpdateView {
    private UpdateInfoContract.UpdatePresenter mPresenter;

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
        setTitle("MVP-V1");
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {


    }

    private void getUpdateInfo() {
        mPresenter = new UpdateInfoPresenter(this);
        mPresenter.getUpdateInfo("");
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
}
