package com.feng.android.third_framework;

import org.junit.Test;

import java.text.DecimalFormat;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        long cur = 19;
        long tot = 100;
        System.out.println((cur/Long.valueOf(tot).floatValue()));
    }
}