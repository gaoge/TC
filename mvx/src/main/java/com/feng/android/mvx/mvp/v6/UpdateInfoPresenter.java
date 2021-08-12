package com.feng.android.mvx.mvp.v6;

import com.feng.android.base.mvp.BasePresenter;
import com.feng.android.lib_framework.net.base.BaseSubscriber;
import com.feng.android.lib_framework.net.entity.UpdateEntity;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-06 10:43
 * @tips
 */
public class UpdateInfoPresenter extends BasePresenter<UpdateInfoContract.UpdateView, UpdateInfoModel> implements UpdateInfoContract.UpdatePresenter {
    //肯定会持有 M和 V
    //一个Presenter 对应多个 Model 怎么解决？ new 很正常，尽量分离(六大基本原则)
    //一般情况下是 1个 Presenter 对应一个Model,
    //如果有一对多的情况，参考 1个 V 对应多个 P 的解决方案



    public UpdateInfoPresenter() {
    }


    @Override
    public void getUpdateInfo(String token) {
        //显示正在加载中
        getView().onLoading();

        getModel().getUpdateInfo(token)
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
