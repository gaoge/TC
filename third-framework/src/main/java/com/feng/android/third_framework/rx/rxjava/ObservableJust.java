package com.feng.android.third_framework.rx.rxjava;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-30 14:30
 * @tips
 */
public class ObservableJust<T> extends Observable<T> {
    private T item;
    public ObservableJust(T item) {
        this.item = item;
    }

    @Override
    protected void suscribeActual(Observer<T> observer) {
        //代理对象 ,why? 方便方法扩展
        ScalarDisposable sd = new ScalarDisposable(observer,item);
        observer.onSubscribe();
        sd.run();
    }

    private class ScalarDisposable<T> {
        private Observer observer;
        private T item;

        public ScalarDisposable(Observer<T> observer, T item) {
            this.observer = observer;
            this.item = item;
        }

        public void run(){
            try{
                observer.onNext(item);
                observer.onComplete();
            }catch (Exception e){
                observer.onError(e);
            }
        }
    }
}
