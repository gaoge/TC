package com.feng.android.third_framework.rx.rxjava;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-02 10:36
 * @tips
 */
public class ObserverOnObservable<T> extends Observable<T> {
    Observable<T> source;
    Schedulers schedulers;
    
    public ObserverOnObservable(Observable<T> source, Schedulers schedulers) {
        this.schedulers = schedulers;
        this.source = source;
    }

    @Override
    protected void suscribeActual(Observer<T> observer) {
        source.subscribe(new ObserverOnObserver(observer,schedulers));
    }

    private class ObserverOnObserver implements Observer<T>, Runnable{
        final Observer<T> observer;
        final Schedulers schedulers;
        private T value;

        public ObserverOnObserver(Observer<T> observer, Schedulers schedulers) {
            this.observer = observer;
            this.schedulers = schedulers;
        }

        @Override
        public void onSubscribe() {
            observer.onSubscribe();
        }

        @Override
        public void onNext(T item) {
            value = item;
            schedulers.scheduleDirect(this);
        }

        @Override
        public void onError(Throwable e) {
            observer.onError(e);
        }

        @Override
        public void onComplete() {
            observer.onComplete();
        }

        @Override
        public void run() {
            //主线程
            observer.onNext(value);
        }
    }
}
