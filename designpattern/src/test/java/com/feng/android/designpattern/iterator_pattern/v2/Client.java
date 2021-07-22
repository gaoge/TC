package com.feng.android.designpattern.iterator_pattern.v2;

import com.feng.android.designpattern.iterator_pattern.v1.UserInfo;

import org.junit.Test;

import java.util.ArrayList;

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

        QQuserSystem qQuserSystem = new QQuserSystem();
        WXUserSystem wxUserSystem = new WXUserSystem();
        //两个系统统一放到一个地方，责任链模式讲解
        
        UserInfo loginUserInfo = queryUserInfo(userName,pwd,wxUserSystem.iterator());

        //这个地方走了if else判断
        if(null == loginUserInfo){
            //从QQ的系统里边去获取
            loginUserInfo = queryUserInfo(userName,pwd,qQuserSystem.iterator());
        }

        //很有可能将来会接第三个系统，或者第四个系统

        if(null == loginUserInfo){
            //登录失败，用户名和密码错误
            System.out.println("该用户没有注册");
        }else{
            System.out.println("登录成功");
        }

        //再打一个比如，四个人做开发，有一哥们要去显示列表，
        //但是数据已经被存入到数据库
    }

    private UserInfo queryUserInfo(String userName, String pwd, Iterator<UserInfo> iterator) {
        while(iterator.hasNext()){
            UserInfo userInfo = iterator.next();
            if(userInfo.userName.equals(userName) && userInfo.userPwd.equals(pwd)){
                return userInfo;
            }
        }
        return null;
    }


}
