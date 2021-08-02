package com.feng.android.third_framework.rxjava;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-30 15:36
 * @tips
 */
public class LambdaObserver<T> implements Observer<T> {
    private Consumer<T> mOnNext;

    public LambdaObserver(Consumer<T> onNext) {
        this.mOnNext = onNext;
    }

    @Override
    public void onSubscribe() {

    }

    @Override
    public void onNext(T item) {
        mOnNext.onNext(item);
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
