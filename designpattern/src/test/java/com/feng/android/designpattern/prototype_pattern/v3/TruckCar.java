package com.feng.android.designpattern.prototype_pattern.v3;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-21 14:17
 * @tips
 * 装箱子的卡车
 */
public class TruckCar {
    public IBox box;

    public void addBox(IBox box){
        this.box = box;
    }

    public IBox remove(){
        return box;
    }

}
