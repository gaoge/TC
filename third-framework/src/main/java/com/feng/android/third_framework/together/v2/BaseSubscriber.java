package com.feng.android.third_framework.together.v2;

import rx.Subscriber;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-03 15:31
 * @tips
 */
public abstract class BaseSubscriber<T> extends Subscriber<T> {
    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        //网络异常 ，解析异常， 代码结果处理过程中异常
        if(e instanceof ErrorHandle.ServerError){
            ErrorHandle.ServerError serverError = (ErrorHandle.ServerError) e;
            onError("",serverError.getMessage());
        }else{
            //自己处理
            onError("","未知异常");
        }
    }

    @Override
    public void onNext(T t) {

    }

    protected abstract void onError(String errorCode,String errorMessage);
}
