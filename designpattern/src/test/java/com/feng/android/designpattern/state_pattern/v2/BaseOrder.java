package com.feng.android.designpattern.state_pattern.v2;

import com.feng.android.designpattern.state_pattern.v2.state.OrderOperateState;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-23 10:30
 * @tips
 */
public class BaseOrder {
    protected OrderOperateState mOrderOperateState;

    public void setmOrderOperateState(OrderOperateState mOrderOperateState) {
        this.mOrderOperateState = mOrderOperateState;
    }
}
