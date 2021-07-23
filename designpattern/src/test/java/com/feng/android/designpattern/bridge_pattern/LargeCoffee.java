package com.feng.android.designpattern.bridge_pattern;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-23 13:31
 * @tips
 */
public class LargeCoffee extends Coffee{
    public LargeCoffee(CoffeeAdditives additives) {
        super(additives);
    }

    @Override
    public void makeCoffee() {
        System.out.println("大杯的" + mCoffeeAdditives + "咖啡");
    }
}
