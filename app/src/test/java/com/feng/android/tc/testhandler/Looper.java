package com.feng.android.tc.testhandler;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-15 14:23
 * @tips
 */
public class Looper {
    static final ThreadLocal<Looper> sThreadLocal = new ThreadLocal<Looper>();
    MessageQueue mQueue;

    public Looper(){
        mQueue = new MessageQueue();
    }
    public static void prepare(){
        sThreadLocal.set(new Looper());
    }

    public static void loop() {
        Looper looper = myLooper();
        for(;;){
            MessageQueue queue = looper.mQueue;
            Message message = queue.next();

            if(null == message){
                return;
            }
            message.target.handleMessage(message);

        }
    }

    public static Looper myLooper(){
        return sThreadLocal.get();
    }
}
