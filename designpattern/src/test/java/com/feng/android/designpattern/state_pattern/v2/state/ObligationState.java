package com.feng.android.designpattern.state_pattern.v2.state;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-23 10:27
 * @tips
 * 待付款状态下的操作
 */
public class ObligationState implements OrderOperateState {
    @Override
    public void pay() {
        System.out.println("支付成功");
    }

    @Override
    public void deleverGoods() {
        System.out.println("未完成付款");

    }
}
