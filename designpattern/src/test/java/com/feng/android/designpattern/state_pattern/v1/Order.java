package com.feng.android.designpattern.state_pattern.v1;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-23 10:18
 * @tips
 * 3种状态 第一个版其实够了，但是第二个版本增加了很多状态
 */
public class Order {
    //待付款
    public final int OBLIGATION = 1;
    //已付款
    public final int PAID = 2;
    //待收获
    public final int WAITRECEIVING = 3;
    //待评价
    public final int WAITCOMMENT = 4;
    //订单状态
    public int mStatus = OBLIGATION ;
    //付款
    public void pay(){
        if(mStatus == OBLIGATION){
            mStatus = PAID;
            System.out.println("付款");
        }else{
            System.out.println("不在待付款状态");

        }
    }

    //发货
    public void deliverGoods(){
        if(mStatus == PAID){
            mStatus = WAITRECEIVING;
            System.out.println("发货");
        }else{
            System.out.println("不在已付款状态");

        }
    }
}
