package com.feng.android.designpattern.proxy_pattern.dynamic_proxy;


/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-21 11:06
 * @tips
 * 银行办理业务-被代理的对象-我们
 */
public class Man implements IBank {
    private String name;

    public Man(String name) {
        this.name = name;
    }

    @Override
    public void applyBank() {
        System.out.println(name + "申请办卡");
    }

    @Override
    public void lostBank() {
        System.out.println(name + "申请挂失");
    }

    @Override
    public void extraBank() {
        System.out.println(name + "额外业务");
    }
}
