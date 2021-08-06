package com.feng.android.mvx.mvp.v4;

import com.feng.android.base.mvp.BasePresenter;
import com.feng.android.net.base.BaseSubscriber;
import com.feng.android.net.entity.UpdateEntity;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-06 10:43
 * @tips
 */
public class UpdateInfoPresenter extends BasePresenter<UpdateInfoContract.UpdateView> implements UpdateInfoContract.UpdatePresenter {
    //肯定会持有 M和 V
    private UpdateInfoContract.UpdateInfoModel mModel;


    public UpdateInfoPresenter() {

        mModel = new UpdateInfoModel();
    }


    @Override
    public void getUpdateInfo(String token) {
        //显示正在加载中
        getView().onLoading();

        mModel.getUpdateInfo(token)
                .subscribe(new BaseSubscriber<UpdateEntity>() {
                    @Override
                    protected void onFailure(String errorCode, String errorMessage) {
                        //每次都要去判断 mView != null,这个也很麻烦，怎么办？
                        //都是接口，通用迪马 View != null 统一处理 ，这个是AOP(aspectJ,动态代理)

                        getView().onError(errorMessage);


                    }

                    @Override
                    protected void onSuccess(UpdateEntity updateEntity) {

                        getView().onSucceed(updateEntity);
                    }
                });
    }
}
