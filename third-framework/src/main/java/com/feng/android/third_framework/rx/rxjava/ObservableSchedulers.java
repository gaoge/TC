package com.feng.android.third_framework.rx.rxjava;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-02 10:05
 * @tips
 */
final class ObservableSchedulers<T> extends Observable<T>{
    final Observable<T> source;
    final Schedulers schedulers;


    public ObservableSchedulers(Observable<T> source, Schedulers schedulers) {
        this.source = source;
        this.schedulers = schedulers;
    }

    @Override
    protected void suscribeActual(Observer<T> observer) {
        schedulers.scheduleDirect(new SchedulerTask(observer));
    }

    private class SchedulerTask implements Runnable {
        final  Observer<T> observer;
        public SchedulerTask( Observer<T> observer) {
            this.observer = observer;
        }

        @Override
        public void run() {
            //线程池最终会来执行Runnable -> 这行代码，会执行上游的 subscribe()
            //而这个方法在子线程中执行
            source.subscribe(observer);
        }
    }
}
