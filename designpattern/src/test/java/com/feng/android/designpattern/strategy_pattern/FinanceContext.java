package com.feng.android.designpattern.strategy_pattern;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-19 16:53
 * @tips
 * 策略模式上下文，相当于android里面的Context
 * 可以获取一些额外的基本信息 - 理财有关
 * 这个类有点多余，在实际的开发中可以不写
 */
public class FinanceContext {
    IFinance finance;

    public FinanceContext(IFinance finance){
        this.finance = finance;
    }

    public float finance(int month,int money){
        return finance.finance(month,money);
    }

}
