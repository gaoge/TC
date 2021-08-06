package com.feng.android.base.mvp;

import android.os.Bundle;

import com.feng.android.base.BaseActivity;
import com.feng.android.base.mvp.inject.InjectPresenter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-06 13:09
 * @tips
 */
public abstract class BaseMVPActivity<P extends BasePresenter> extends BaseActivity implements BaseView{

    P mPresenter;

    List<BasePresenter> mPresenters = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //createPresenter() 为抽象模版，方法
        //1. mPresenter的具体类型在父类中无法确定，
        //2. 所以由子类实现该方法，结合泛型，从而返回确认的具体类型
        mPresenter = createPresenter();
        mPresenter.attach(this);

        //1. Activity ? 2. Fragment ? 3. ViewGroup 要抽离代码，工具类抽出去，
        //今天你可以抽离目前的一部分 注入代码 + 额外功能 + 还有一些其它地方又不一样
        injectPresenters();

    }

    private void injectPresenters() {
        //这个地方要去注入 Presenter 通过反射(Dagger)
        Field[] fields = getClass().getDeclaredFields();

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
                    basePresenter.attach(this);

                    //设置对象
                    field.setAccessible(true);
                    field.set(this,basePresenter);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    protected abstract P createPresenter();

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detach();
        for (BasePresenter presenter : mPresenters) {
            presenter.detach();
        }
    }

    public P getPresenter() {
        return mPresenter;
    }
}
