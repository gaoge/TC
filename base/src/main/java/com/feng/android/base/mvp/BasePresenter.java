package com.feng.android.base.mvp;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-06 12:57
 * @tips
 */
public class BasePresenter<V extends BaseView> {
    //attach 传递的时候 会有不同的View,怎么办？ 泛型

    private V mView;

    public void attach(V view){
        this.mView = view;
    }

    public void detach(){
        this.mView = null;
    }

    public V getView() {
        return mView;
    }
}
