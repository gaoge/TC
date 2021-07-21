package com.feng.android.designpattern.proxy_pattern.dynamic_proxy;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-21 11:19
 * @tips
 * 银行办理业务 - 动态代理 - InvocationHandler
 */
public class BankInvocationHandler implements InvocationHandler {

    /**
     * 被代理的对象
     */
    private Object mObject;

    public BankInvocationHandler(Object mObject) {
        this.mObject = mObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //执行方法，目标接口调用的方法都会来到这里面
//        System.out.println(" methodName = " + method.getName());
//        System.out.println(" params = " + args.toString());
        System.out.println("开始受理");
        //调用被代理对象的方法
        Object voidObject = method.invoke(mObject,args);
        System.out.println("操作完毕");


        return voidObject;
    }
}
