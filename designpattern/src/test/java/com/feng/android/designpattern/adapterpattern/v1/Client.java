package com.feng.android.designpattern.adapterpattern.v1;

import org.junit.Test;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-20 12:11
 * @tips
 * 类适配
 */
public class Client {

    @Test
    public void main(){
        //第一个版本只是显示人民币
        RMBAdapter rmbAdapter = new RMBAdapter(500);
        float rmb = rmbAdapter.getRmb();
        System.out.println("人民币: " + rmb);

        //第二个版本要去兼容美元，这里直接在RMBAdapter里增加方法
        float usd = rmbAdapter.getUsd();
        System.out.println("美元: " + usd);
    }
}
