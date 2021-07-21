package com.feng.android.designpattern.prototype_pattern.v3;


/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-21 14:14
 * @tips
 * 具体的出货的物品 - 塑料夹子
 */
public class PlasticClampBox extends IBox {
    private int number;
    private String name;
    @Override
    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
