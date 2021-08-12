package com.feng.android.mvx.mvp.v2;

import com.feng.android.lib_framework.net.base.BaseSubscriber;
import com.feng.android.lib_framework.net.entity.UpdateEntity;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-06 10:43
 * @tips
 */
public class UpdateInfoPresenter implements UpdateInfoContract.UpdatePresenter {
    //肯定会持有 M和 V
    private UpdateInfoContract.UpdateInfoModel mModel;
    private UpdateInfoContract.UpdateView mView;

    public UpdateInfoPresenter() {
        this.mView = mView;
        mModel = new UpdateInfoModel();
    }

    //解绑View,加了这个之后越来越复杂，代码写起来越写越多？怎么办？
    //问题是，1. 很多代码是公用，反复的 attch,detach 每个 P 都要与哦
    //2. 每个V（Activity） 都要有
    public void attach(UpdateInfoContract.UpdateView view){
        mView = view;
    }

    public void detach(){
        mView = null;
    }

    @Override
    public void getUpdateInfo(String token) {
        //显示正在加载中
        mView.onLoading();

        mModel.getUpdateInfo(token)
                .subscribe(new BaseSubscriber<UpdateEntity>() {
                    @Override
                    protected void onFailure(String errorCode, String errorMessage) {
                        if(null != mView){
                            mView.onError(errorMessage);
                        }

                    }

                    @Override
                    protected void onSuccess(UpdateEntity updateEntity) {
                        if(null != mView){
                            mView.onSucceed(updateEntity);
                        }
                    }
                });
    }
}
