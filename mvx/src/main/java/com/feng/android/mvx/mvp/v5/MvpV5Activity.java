package com.feng.android.mvx.mvp.v5;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.feng.android.base.mvp.BaseMVPActivity;
import com.feng.android.base.mvp.inject.InjectPresenter;
import com.feng.android.mvx.R;
import com.feng.android.net.entity.UpdateEntity;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-06 11:26
 * @tips
 */
//这个地方可以放一个 泛型 ，方便 1 对 1，
public class MvpV5Activity extends BaseMVPActivity<UpdateInfoPresenter> implements UpdateInfoContract.UpdateView {

    //遗留问题： 多 Presenter怎么处理，dagger, 自己写Dagger处理

    //一个 View里面肯定有多个 Presenter 的情况，怎么处理？ dagger 处理，自己写一个注入
    //多个presenter 自己手动去 attach 和 detach

    @InjectPresenter
    UpdateInfoPresenter mPresenter1;

    @InjectPresenter
    UpdateInfoPresenter mPresenter2;

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
        setTitle("MVP-V4");
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {


    }

    private void getUpdateInfo() {
        mPresenter1.getUpdateInfo("");
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
