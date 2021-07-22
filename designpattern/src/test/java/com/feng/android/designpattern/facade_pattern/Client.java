package com.feng.android.designpattern.facade_pattern;


import org.junit.Test;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-21 16:23
 * @tips
 * 采用迭代器模式
 */
public class Client {

    @Test
    public void main(){
        String userName = "gg";
        String pwd = "123456";
        //根据用户名和密码去查询用户信息，
        // 如果没有查询到，那么代表登录失败；否则代表登录成功


        UserSystemFacade userSystemFacade = new UserSystemFacade();

        UserInfo loginUserInfo = userSystemFacade.queryUserInfo(userName,pwd);

        System.out.println(loginUserInfo);

    }

}
