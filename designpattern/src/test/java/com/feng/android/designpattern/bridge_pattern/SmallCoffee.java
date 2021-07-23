package com.feng.android.designpattern.bridge_pattern;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-23 13:31
 * @tips
 */
public class SmallCoffee extends Coffee{
    public SmallCoffee(CoffeeAdditives additives) {
        super(additives);
    }

    @Override
    public void makeCoffee() {
        System.out.println("小杯的" + mCoffeeAdditives + "咖啡");
    }
}
