package com.feng.android.designpattern.facade_pattern;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-22 15:59
 * @tips
 * 责任链设计模式 - 抽象处理者接口
 */
public interface IUserSystemHandler<T extends IUserSystemHandler> {
    void nextHandler(T systemHandler);
}
