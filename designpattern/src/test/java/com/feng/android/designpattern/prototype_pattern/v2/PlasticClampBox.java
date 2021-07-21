package com.feng.android.designpattern.prototype_pattern.v2;


/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-21 14:14
 * @tips
 * 具体的出货的物品 - 塑料夹子
 */
public class PlasticClampBox implements IBox {
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

    @Override
    public IBox copy() {
        PlasticClampBox box = new PlasticClampBox();
        box.setName(name);
        box.setNumber(number);
        return box;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
