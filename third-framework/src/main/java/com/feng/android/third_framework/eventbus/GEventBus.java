package com.feng.android.third_framework.eventbus;

import android.os.Handler;
import android.os.Looper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-23 17:39
 * @tips
 */
public class GEventBus {

    private final Map<Class<?>, CopyOnWriteArrayList<Subscription>> subscriptionsByEventType;
    private final Map<Object, List<Class<?>>> typesBySubscriber;

    static volatile GEventBus defaultInstance;
    private GEventBus(){
        subscriptionsByEventType = new HashMap<>();
        typesBySubscriber = new HashMap<>();
    }

    public static GEventBus getDefault() {
        GEventBus instance = defaultInstance;
        if (instance == null) {
            synchronized (GEventBus.class) {
                instance = GEventBus.defaultInstance;
                if (instance == null) {
                    instance = GEventBus.defaultInstance = new GEventBus();
                }
            }
        }
        return instance;
    }

    public void register(Object object) {
        //1.解析所有方法，封装成SubscriberMethod的集合
        List<SubscriberMethod> subscriberMethods = new ArrayList<>();
        Class<?> objClass = object.getClass();
        Method[] declaredMethods = objClass.getDeclaredMethods();
        for(Method method : declaredMethods){
            Subscribe subscribe = method.getAnnotation(Subscribe.class);
            if(null != subscribe){
                //所有的Subscribe属性 解析出来
                Class<?>[] parameterTypes = method.getParameterTypes();
                SubscriberMethod subscriberMethod = new SubscriberMethod(method,parameterTypes[0],subscribe.threadMode(),subscribe.priority(),subscribe.sticky());
                subscriberMethods.add(subscriberMethod);
            }
        }
        //2.按照规则存放到subscriptionsByEventType里面去
        for (SubscriberMethod subscriberMethod : subscriberMethods) {
            subscriber(object,subscriberMethod);
        }
    }

    //2.按照规则存放到subscriptionsByEventType里面去
    private void subscriber(Object object, SubscriberMethod subscriberMethod) {
        Class<?> eventType = subscriberMethod.eventType;
        CopyOnWriteArrayList<Subscription> subscriptions = subscriptionsByEventType.get(eventType);
        if(null == subscriptions){
            subscriptions = new CopyOnWriteArrayList<>();
            subscriptionsByEventType.put(eventType,subscriptions);
        }
        //判断优先级 不写
        Subscription subscription = new Subscription(object,subscriberMethod);
        subscriptions.add(subscription);

        //typesBySubscriber要弄好，是为了方便移除
        List<Class<?>> eventTypes = typesBySubscriber.get(object);
        if(null == eventTypes){
            eventTypes = new ArrayList<>();
            typesBySubscriber.put(object,eventTypes);
        }
        if(!eventTypes.contains(eventType)){
            eventTypes.add(eventType);
        }
    }

    public void unregister(Object object) {
        List<Class<?>> eventTypes = typesBySubscriber.get(object);
        if(null != eventTypes){
            for (Class<?> eventType : eventTypes) {
                removeObject(eventType,object);
            }
        }
    }

    private void removeObject(Class<?> eventType, Object object) {
        //一边移除，一边循环不行
        List<Subscription> subscriptions = subscriptionsByEventType.get(eventType);
        if (subscriptions != null) {
            int size = subscriptions.size();
            for (int i = 0; i < size; i++) {
                Subscription subscription = subscriptions.get(i);
                if (subscription.subscriber == object) {
                    subscriptions.remove(i);
                    i--;
                    size--;
                }
            }
        }
    }

    public void post(Object event) {
        //遍历subscriptionsByEventType,找到符合的方法调用方法的method.invoke()执行，要注意线程切换
        Class<?> eventType = event.getClass();
        CopyOnWriteArrayList<Subscription> subscriptions = subscriptionsByEventType.get(eventType);

        if(null != subscriptions){
            for (Subscription subscription : subscriptions){
                executeMethod(subscription,event);
            }
        }

    }

    private void executeMethod(Subscription subscription, Object event) {
        ThreadMode threadMode = subscription.subscriberMethod.threadMode;
        boolean isMainThread = Looper.getMainLooper() == Looper.myLooper();
        switch (threadMode){
            case POSTING:
                invokeMethod(subscription,event);
                break;
            case MAIN:
                if(isMainThread){
                    invokeMethod(subscription,event);
                }else{
                    //行不行，不行？行？
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            invokeMethod(subscription,event);
                        }
                    });
                }
                break;
            case ASYNC:
                AsyncPoster.enqueue(subscription,event);
                break;
            case BACKGROUND:
                if(!isMainThread){
                    invokeMethod(subscription,event);
                }else{
                    AsyncPoster.enqueue(subscription,event);
                }
                break;
        }
    }

    private void invokeMethod(Subscription subscription, Object event) {
        try {
            subscription.subscriberMethod.method.invoke(subscription.subscriber,event);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
