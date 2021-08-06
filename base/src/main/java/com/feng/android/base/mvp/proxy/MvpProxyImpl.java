package com.feng.android.base.mvp.proxy;

import com.feng.android.base.mvp.BasePresenter;
import com.feng.android.base.mvp.BaseView;
import com.feng.android.base.mvp.inject.InjectPresenter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-06 17:25
 * @tips
 * 实现
 */
public class MvpProxyImpl<V extends BaseView> implements IBaseMvpProxy {

    private V mView;
    List<BasePresenter> mPresenters = new ArrayList<>();

    public MvpProxyImpl(V view){
        //可以做一下判断，是不是 null,
        this.mView = view;
    }

    @Override
    public void bindAndCreatePresenter() {
        injectPresenters();
    }


    @Override
    public void unbindPresenter() {
        mView = null;
        for (BasePresenter presenter : mPresenters) {
            presenter.detach();
        }
    }

    private void injectPresenters() {
        //这个地方要去注入 Presenter 通过反射(Dagger)
        Field[] fields = mView.getClass().getDeclaredFields();

        for (Field field : fields){
            InjectPresenter annotation = field.getAnnotation(InjectPresenter.class);
            if(null != annotation){
                Class<? extends BasePresenter> presenterClass;
                //自己去判断一下类型？ 获取继承的父类，如果不是继承 BasePresenter ，抛异常
                try{
                    // 创建注入
                    presenterClass = (Class<? extends BasePresenter>) field.getType();
                }catch (Exception e){
                    //乱七八糟的一些注入
                    throw new RuntimeException("no suport inject presenter type! " + field.getType().getName());
                }
                try {
                    //创建对象
                    BasePresenter basePresenter = presenterClass.newInstance();
                    mPresenters.add(basePresenter);
                    //并没有解绑，还是会有问题，这个怎么办？
                    basePresenter.attach(mView);

                    //设置对象
                    field.setAccessible(true);
                    field.set(mView,basePresenter);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
