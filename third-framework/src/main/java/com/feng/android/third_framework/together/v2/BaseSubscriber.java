package com.feng.android.third_framework.together.v2;

import rx.Subscriber;
import timber.log.Timber;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-03 15:31
 * @tips
 */
public abstract class BaseSubscriber<T> extends Subscriber<T> {
    @Override
    public void onCompleted() {
        Timber.e("BonCompleted()");
    }

    @Override
    public void onError(Throwable e) {
        Timber.e("BonError()");
        //网络异常 ，解析异常， 代码结果处理过程中异常
        if(e instanceof ErrorHandle.ServerError){
            ErrorHandle.ServerError serverError = (ErrorHandle.ServerError) e;
            onFailure("","[server error] : " + serverError.errorMsg);
        }else if(e instanceof ErrorHandle.HttpError){
            ErrorHandle.HttpError httpError = (ErrorHandle.HttpError) e;
            onFailure("","[http error] : " + httpError.errorMessage );
        }
        else{
            //自己处理
            onFailure("","[unknown error] : " + e.getMessage());
        }
    }

    @Override
    public void onNext(T t) {
        Timber.e("BonNext()");

        onSuccess(t);
    }

    protected abstract void onFailure(String errorCode, String errorMessage);

    protected abstract void onSuccess(T t);
}
