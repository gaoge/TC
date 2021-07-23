package com.feng.android.designpattern.bridge_pattern;

import org.junit.Test;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-23 13:34
 * @tips
 */
public class Client {

    @Test
    public void main(){
        Sugar sugar = new Sugar();
        SmallCoffee smallCoffee = new SmallCoffee(sugar);
        smallCoffee.makeCoffee();

        Original original = new Original();
        LargeCoffee largeCoffee = new LargeCoffee(original);
        largeCoffee.makeCoffee();
    }
}
