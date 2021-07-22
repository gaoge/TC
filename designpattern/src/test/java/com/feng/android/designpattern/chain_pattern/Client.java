package com.feng.android.designpattern.chain_pattern;


import com.feng.android.designpattern.chain_pattern.handler.AbsUserSystemHandler;
import com.feng.android.designpattern.chain_pattern.handler.IUserSystem;
import com.feng.android.designpattern.chain_pattern.handler.UserInfo;

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
        String userName = "g";
        String pwd = "123456";
        //根据用户名和密码去查询用户信息，
        // 如果没有查询到，那么代表登录失败；否则代表登录成功

        WXUserSystem wxUserSystem = new WXUserSystem();
        QQuserSystem qQuserSystem = new QQuserSystem();

        wxUserSystem.nextHandler(qQuserSystem);


        UserInfo loginUserInfo = wxUserSystem.queryUserInfo(userName,pwd);

        //两个系统统一放到一个地方，责任链模式讲解


        if(null == loginUserInfo){
            //登录失败，用户名和密码错误
            System.out.println("该用户没有注册");
        }else{
            System.out.println("登录成功");
        }

    }

}
