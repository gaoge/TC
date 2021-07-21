package com.feng.android.designpattern.strategypattern;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-19 16:37
 * @tips
 */
public class ZhifubaoFinance implements IFinance{
    @Override
    public float finance(int month, int money) {
        return 12000;
    }
}
