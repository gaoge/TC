package com.feng.android.designpattern.bridge_pattern;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-23 11:47
 * @tips
 * 咖啡 大杯，小杯 抽象
 */
public abstract class Coffee {
    protected CoffeeAdditives mCoffeeAdditives;

    public Coffee(CoffeeAdditives additives){
        this.mCoffeeAdditives = additives;
    }

    //生成一杯咖啡
    public abstract void  makeCoffee();
}
