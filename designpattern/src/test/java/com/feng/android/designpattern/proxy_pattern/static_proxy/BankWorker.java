package com.feng.android.designpattern.proxy_pattern.static_proxy;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-21 11:05
 * @tips
 * 银行办理业务 - 代理对象： 业务员
 */
public class BankWorker implements IBank {

    //持有被代理的读喜庆
    private IBank bank;

    public BankWorker(IBank bank) {
        this.bank = bank;
    }

    @Override
    public void applyBank() {
        System.out.println("开始受理");
        bank.applyBank();
        System.out.println("操作完毕");
    }

    @Override
    public void lostBank() {
        System.out.println("开始受理");
        bank.lostBank();
        System.out.println("操作完毕");
    }
}
