package com.feng.android.insurance;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-20 17:51
 * @tips
 * 被观察者
 */
public class Observable<M,T extends Observer<M>> {
    private List<T> observers;

    public Observable() {
        this.observers = new ArrayList<>();
    }

    public void register(T observer){
        observers.add(observer);
    }

    public void unregister(T observer){
        observers.remove(observer);
    }

    public void addUpdate(M m){
        for(T observer: observers){
            observer.update(m);
        }
    }
}
