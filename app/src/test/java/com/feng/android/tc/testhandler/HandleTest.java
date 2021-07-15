package com.feng.android.tc.testhandler;

import org.junit.Test;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-15 14:24
 * @tips
 */
public class HandleTest {
    @Test
//    public static void main(String[] args) {
    public void main() {

        Looper.prepare();

        ActivityThread thread = new ActivityThread();
        thread.attach(false);


        Looper.loop();
    }

    @Test
    public void test(){
        System.out.println("test method");
    }
}
