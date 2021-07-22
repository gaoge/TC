package com.feng.android.designpattern.facade_pattern;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-22 15:58
 * @tips
 * 责任链设计模式 - 抽象处理者角色
 */
public abstract class AbsUserSystemHandler implements IUserSystemHandler<AbsUserSystemHandler>, IUserSystem {
    private AbsUserSystemHandler nextHandler;

    public AbsUserSystemHandler getNextHandler() {
        return nextHandler;
    }

    public void nextHandler(AbsUserSystemHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
}
