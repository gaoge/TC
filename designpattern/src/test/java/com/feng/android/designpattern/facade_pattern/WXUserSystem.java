package com.feng.android.designpattern.facade_pattern;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-21 16:18
 * @tips
 * 微信的用户系统 - 用户系统 - 数组存储
 */
public class WXUserSystem extends AbsUserSystemHandler {

    private UserInfo[] userInfos = new UserInfo[3];

    public WXUserSystem() {
        this.userInfos = userInfos;
        userInfos[0] = new UserInfo("cc","123456","001","男");
        userInfos[1] = new UserInfo("dd","123456","002","男");
        userInfos[2] = new UserInfo("ee","123456","003","男");
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
