package com.feng.android.designpattern.strategy_pattern;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-19 16:38
 * @tips
 */
public class FengjrFinance implements IFinance {
    @Override
    public float finance(int month, int money) {
        return 15000;
    }
}
