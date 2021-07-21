package com.feng.android.designpattern.proxy_pattern.dynamic_proxy;




import org.junit.Test;

import java.lang.reflect.Proxy;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-21 11:15
 * @tips
 */
public class Client {

    @Test
    public void main(){
        Man man = new Man("gg");

        //返回的是IBank的一个实例对象，这个对象是由Java给我们创建的,调用的是jni
        IBank bank = (IBank) Proxy.newProxyInstance(
                IBank.class.getClassLoader(),//ClassLoader,
                new Class<?>[]{IBank.class}, //目标接口
                new BankInvocationHandler(man) //InvocationHandler(这个类是关键)
        );

        //当调用这个方法的时候，会来到BankInvocationHandler的invoke方法
        bank.applyBank();
        bank.lostBank();
        bank.extraBank();
    }

}
