package com.feng.android.designpattern.flyweight_pattern;

import java.util.Random;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-22 17:32
 * @tips
 */
public class Ticket {
    String from;
    String to;

    public Ticket(String from, String to) {
        this.from = from;
        this.to = to;
    }

    int getPrice(){
        return new Random().nextInt(100) + 20;
    }

}
