package com.feng.android.designpattern.proxy_pattern.retrofit;

import org.junit.Test;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-21 13:47
 * @tips
 */
public class Client {

    @Test
    public void main(){
        GGRetrofit ggRetrofit = new GGRetrofit();
        //核心代码 ServiceInterface.class 接口 Class会返回一个ServiceInterface的实例
        ServiceInterface serviceInterface = ggRetrofit.create(ServiceInterface.class);

        String result = serviceInterface.login();
        System.out.println("返回值： " + result);
    }
}
