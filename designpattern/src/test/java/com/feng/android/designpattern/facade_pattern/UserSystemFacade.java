package com.feng.android.designpattern.facade_pattern;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-22 17:08
 * @tips
 * 门面设计模式 - 易于使用的高层次
 */
public class UserSystemFacade implements IUserSystem{

    //第一个应该检查的系统
    AbsUserSystemHandler mFirstSystemHandler;

    public UserSystemFacade(){
        mFirstSystemHandler = new WXUserSystem();
        QQuserSystem qQuserSystem = new QQuserSystem();

        mFirstSystemHandler.nextHandler(qQuserSystem);
    }


    @Override
    public UserInfo queryUserInfo(String userName, String userPwd) {
        return mFirstSystemHandler.queryUserInfo(userName,userPwd);
    }
}
