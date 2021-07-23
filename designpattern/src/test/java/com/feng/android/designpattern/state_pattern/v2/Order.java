package com.feng.android.designpattern.state_pattern.v2;

import com.feng.android.designpattern.state_pattern.v2.state.ObligationState;
import com.feng.android.designpattern.state_pattern.v2.state.OrderOperateState;
import com.feng.android.designpattern.state_pattern.v2.state.PaidState;
import com.feng.android.designpattern.state_pattern.v2.state.WaitReveingState;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-23 10:35
 * @tips
 */
public class Order extends BaseOrder implements OrderOperateState {

    public Order() {
        //默认的状态
        mOrderOperateState = new ObligationState();
    }

    @Override
    public void pay() {
        mOrderOperateState.pay();
        //状态设置为已支付状态
        setmOrderOperateState(new PaidState());
    }

    @Override
    public void deleverGoods() {
        mOrderOperateState.deleverGoods();
        //状态设置为待收获状态
        setmOrderOperateState(new WaitReveingState());
    }
}
