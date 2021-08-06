package com.feng.android.mvx.mvp.v1;

import com.feng.android.net.base.BaseSubscriber;
import com.feng.android.net.entity.UpdateEntity;

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

    public UpdateInfoPresenter(UpdateInfoContract.UpdateView mView) {
        this.mView = mView;
        mModel = new UpdateInfoModel();
    }

    @Override
    public void getUpdateInfo(String token) {
        //显示正在加载中
        mView.onLoading();

        mModel.getUpdateInfo(token)
                .subscribe(new BaseSubscriber<UpdateEntity>() {
                    @Override
                    protected void onFailure(String errorCode, String errorMessage) {
                        mView.onError(errorMessage);
                    }

                    @Override
                    protected void onSuccess(UpdateEntity updateEntity) {
                        mView.onSucceed(updateEntity);
                    }
                });
    }
}
