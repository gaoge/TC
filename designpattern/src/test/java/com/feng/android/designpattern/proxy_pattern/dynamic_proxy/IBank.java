package com.feng.android.designpattern.proxy_pattern.dynamic_proxy;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-21 11:04
 * @tips
 * 银行办理业务 -目标接口
 */
public interface IBank {
    void applyBank();
    void lostBank();
    void extraBank();
}
