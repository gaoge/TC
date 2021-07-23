package com.feng.android.designpattern.state_pattern.v2.state;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-23 10:27
 * @tips
 * 入款状态下的操作
 */
public class PaidState  implements OrderOperateState {
    @Override
    public void pay() {
        System.out.println("不要重复支付");

    }

    @Override
    public void deleverGoods() {
        System.out.println("发货成功");
    }
}
