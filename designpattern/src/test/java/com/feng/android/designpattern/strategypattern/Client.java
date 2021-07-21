package com.feng.android.designpattern.strategypattern;

import org.junit.Test;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-19 16:39
 * @tips
 */
public class Client {


    @Test
    public void main() {
        //这个就是采用了策略设计模式，说白了就是在原来的基础上进行了分离
        IFinance finance = new FengjrFinance();
        //上下文
        FinanceContext financeContext = new FinanceContext(finance);
        System.out.println(financeContext.finance(3,10000));

        //跟工厂模式很像
    }
}
