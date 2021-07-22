package com.feng.android.designpattern.chain_pattern;

import com.feng.android.designpattern.chain_pattern.handler.AbsUserSystemHandler;
import com.feng.android.designpattern.chain_pattern.handler.UserInfo;

import java.util.ArrayList;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-21 16:19
 * @tips
 * QQ 用户系统 - 列表存储
 */
public class QQuserSystem extends AbsUserSystemHandler {

    private ArrayList<UserInfo> userInfos = new ArrayList<>();

    public QQuserSystem() {
        userInfos.add(new UserInfo("gg","123456","001","男"));
        userInfos.add(new UserInfo("aa","123456","002","男"));
        userInfos.add(new UserInfo("bb","123456","003","男"));

    }

    @Override
    public UserInfo queryUserInfo(String userName, String userPwd) {
        //查询用户信息
        //自己查询一把，有就返回，没有就交给下一个
        for(UserInfo userInfo : userInfos){
            if(userInfo.userName.equals(userName) && userInfo.userPwd.equals(userPwd)){
                return userInfo;
            }
        }
        //没有就交给下一个
        AbsUserSystemHandler nextHandler = getNextHandler();
        if(null != nextHandler){
            return nextHandler.queryUserInfo(userName,userPwd);
        }
        return null;
    }
}
