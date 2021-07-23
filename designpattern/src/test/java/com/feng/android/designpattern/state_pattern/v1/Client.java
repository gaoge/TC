package com.feng.android.designpattern.state_pattern.v1;

import org.junit.Test;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-23 10:25
 * @tips
 */
public class Client {

    @Test
    public void main(){
        Order order = new Order();
        order.pay();
        order.deliverGoods();
    }
}
