package com.feng.android.third_framework.rx.rxjava;


import android.graphics.Bitmap;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-30 14:23
 * @tips
 * 被观察者
 */
public abstract class Observable<T> implements ObservableSource<T>{


    private static <T> Observable<T> onAssembly(Observable<T> source) {
        //留出来了
        return source;
    }

    @Override
    public void subscribe(Observer<T> observer) {
        suscribeActual(observer);
    }

    public void subscribe(Consumer<T> onNext){
        subscribe(onNext,null,null);
    }

    private void subscribe(Consumer<T> onNext, Consumer<T> error, Consumer<T> complete) {
        subscribe(new LambdaObserver<>(onNext));
    }

    protected abstract void suscribeActual(Observer<T> observer);

    //操作符 just
    public static <T> Observable<T> just(T item) {
        return onAssembly(new ObservableJust<T>(item));
    }
    //操作符 map
    public <R> Observable<R> map(Function<T, R> function){
        return onAssembly(new ObservableMap<>(this,function));
    }

    public Observable<Bitmap> subscribeOn(Schedulers schedulers){
        return onAssembly(new ObservableSchedulers(this,schedulers));
    }

    public  Observable<T> observerOn(Schedulers schedulers){
        return onAssembly(new ObserverOnObservable(this,schedulers));
    }
}
