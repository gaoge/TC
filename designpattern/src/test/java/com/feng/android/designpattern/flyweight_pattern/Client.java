package com.feng.android.designpattern.flyweight_pattern;

import org.junit.Test;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-22 17:34
 * @tips
 * 享元模式
 */
public class Client {

    @Test
    public void main(){
        for(int i=0;i<1000;i++){
            Ticket ticket = TicketFactory.getTicket("深圳","长沙");
        }
    }
}
