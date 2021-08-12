package com.feng.android.mvx.mvp.v6;

import com.feng.android.base.mvp.BaseView;
import com.feng.android.lib_framework.net.entity.UpdateEntity;

import rx.Observable;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-06 10:42
 * @tips 这个类可有可无，一个协议类,多人协作开发，就可以先把这个写好
 */
public class UpdateInfoContract {
    //user View层
    interface UpdateView extends BaseView {
        void onLoading();
        void onError(String error);
        void onSucceed(UpdateEntity userInfo);
    }

    //user presenter层
    interface UpdatePresenter{
        void getUpdateInfo(String token);
        void attach(UpdateView view);
        void detach();
    }

    //Model层定义接口，外部只需关系Model返回的数据，无需关心内部细节，如是否使用缓存
    interface UpdateInfoModel{
        Observable<UpdateEntity> getUpdateInfo(String token);
    }
}
