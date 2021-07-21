package com.feng.android.designpattern.prototype_pattern.v1;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-21 14:13
 * @tips
 * 出货的箱子接口
 */
public interface IBox {
    void setNumber(int number);//设置箱子的数量
    int getNumber();//有多少货
}
