package com.feng.android.third_framework.rxjava;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-30 14:30
 * @tips
 * 静态代理的一个包裹类
 */
public class ObservableMap<T,R> extends Observable<R> {
    final Observable<T> source; //持有前面的Observable
    final Function<T,R> function; //当前转换


    public ObservableMap(Observable<T> source, Function<T, R> function) {
        this.source = source;
        this.function = function;
    }

    @Override
    protected void suscribeActual(Observer<R> observer) {
        //对observer 包裹了一层，静态代理包裹 source 永远是上游的Observable 对象
        //observer 代表的是下游给我们封装好的observer对象
        source.subscribe(new MapObserver<>(observer,function));
    }

    private class MapObserver<T> implements Observer<T>{

        final Observer<R> observer;
        final Function<T, R> function;
        public MapObserver(Observer<R> observer, Function<T, R> function) {
            this.observer = observer;
            this.function = function;
        }

        @Override
        public void onSubscribe() {
            observer.onSubscribe();
        }

        @Override
        public void onNext(T item) {
            //item 是 String xxxUrl
            //要去转换 String -> Bitmap
            try {
                R applyR = function.apply(item);
                //把 Bitmap 传出去
                observer.onNext(applyR);
            } catch (Exception e) {
                e.printStackTrace();
                observer.onError(e);
            }

        }

        @Override
        public void onError(Throwable e) {
            observer.onError(e);
        }

        @Override
        public void onComplete() {
            observer.onComplete();
        }
    }
}
