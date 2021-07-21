package com.feng.android.designpattern.proxy_pattern.static_proxy;

import org.junit.Test;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-21 11:09
 * @tips
 */
public class Client {

    @Test
    public void main(){
        Man man = new Man("gg");
        BankWorker bankWorker = new BankWorker(man);
        bankWorker.applyBank();
    }
}
