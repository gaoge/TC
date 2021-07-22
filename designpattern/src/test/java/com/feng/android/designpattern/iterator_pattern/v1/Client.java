package com.feng.android.designpattern.iterator_pattern.v1;

import org.junit.Test;

import java.util.ArrayList;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-21 16:23
 * @tips
 * 一般写法：没有采用设计模式之前
 */
public class Client {

    @Test
    public void main(){
        String userName = "g";
        String pwd = "123456";
        //根据用户名和密码去查询用户信息，
        // 如果没有查询到，那么代表登录失败；否则代表登录成功
        UserInfo loginUserInfo = queryWXUserInfo(userName,pwd);
        if(null == loginUserInfo){
            //从QQ的系统里边去获取
            loginUserInfo = queryQQUserInfo(userName,pwd);
        }

        //很有可能将来会接第三个系统，或者第四个系统

        if(null == loginUserInfo){
            //登录失败，用户名和密码错误
            System.out.println("该用户没有注册");
        }else{
            System.out.println("登录成功");

        }
    }

    private UserInfo queryQQUserInfo(String userName, String userPwd) {
        QQuserSystem qqUserSystem = new QQuserSystem();
        ArrayList<UserInfo> userInfos = qqUserSystem.getUserInfos();
        for(UserInfo userInfo : userInfos){
            if(userInfo.userName.equals(userName) && userInfo.userPwd.equals(userPwd)){
                return userInfo;
            }
        }
        return null;
    }


    /**
     *
     * @param userName
     * @param userPwd
     * @return
     * 从微信中查询用户信息
     */
    private UserInfo queryWXUserInfo(String userName, String userPwd) {
        WXUserSystem wxUserSystem = new WXUserSystem();
        UserInfo[] userInfos = wxUserSystem.getUserInfos();
        for(UserInfo userInfo : userInfos){
            if(userInfo.userName.equals(userName) && userInfo.userPwd.equals(userPwd)){
                return userInfo;
            }
        }
        return null;
    }
}
