package com.feng.android.designpattern.flyweight_pattern;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-22 17:33
 * @tips
 */
public class TicketFactory {
    //做一个缓存
    static Map<String,Ticket> sTickMap = new HashMap<>();

    public static  Ticket getTicket(String from,String to){
        String key = from + "-" + to;
        Ticket ticket = sTickMap.get(key);
        if(null == ticket){
            ticket = new Ticket(from,to);
            sTickMap.put(key,ticket);
        }
        return ticket;
    }
}
