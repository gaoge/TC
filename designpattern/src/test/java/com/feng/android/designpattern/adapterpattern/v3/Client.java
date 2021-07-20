package com.feng.android.designpattern.adapterpattern.v3;

import org.junit.Test;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-20 12:11
 * @tips
 * 对象适配
 */
public class Client {

    @Test
    public void main(){
        //第一个版本只是显示人民币
        RMBAdapter rmbAdapter = new RMBAdapter(500);
        Adapter adapter = new Adapter(rmbAdapter);
        float usd = adapter.getUsd();
        System.out.println("美元: " + usd);
    }
}
