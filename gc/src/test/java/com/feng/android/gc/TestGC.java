package com.feng.android.gc;

import org.junit.Test;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-06 18:02
 * @tips
 */
public class TestGC {
    @Test
    void main(){
        A a = new A();
        B b = new B();
        a.instace = b;
        b.instace = a;

        System.gc();

        try{
            Thread.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static class A{
        public B instace;

        @Override
        protected void finalize() throws Throwable {
            System.out.println("A.finalize");
            super.finalize();
        }
    }

    public static class B{
        public A instace;

        @Override
        protected void finalize() throws Throwable {
            System.out.println("B.finalize");
            super.finalize();
        }
    }

}
