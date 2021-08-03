package com.feng.android.third_framework.retrofit.my;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.OkHttpClient;
import timber.log.Timber;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-02 17:36
 * @tips
 */
public class Retrofit {

    final String baseUrl;
    final okhttp3.Call.Factory callFactory;
    private Map<Method,ServiceMethod> serviceMethodMapCache = new ConcurrentHashMap<>();

    private Retrofit(Builder builder){
        this.baseUrl = builder.baseUrl;
        this.callFactory = builder.callFactory;
    }

    public <T> T create(Class<T> service) {
        //检验，是不是一个接口，不能让他继承自子接口

        //重点
        return (T) Proxy.newProxyInstance(service.getClassLoader(),
                new Class[]{service},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //每执行一个方法，都会来这里
                        Timber.e("invoke(): "+ method.getName());

                        //判断是不是Object 的方法
                        if(method.getDeclaringClass() == Object.class){
                            return method.invoke(this,args);
                        }

                        //解析参数注解
                        ServiceMethod serviceMethod = loadServiceMethod(method);

                        //2. 封装OkHttpCall
                        OkHttpCall okHttpCall = new OkHttpCall(serviceMethod,args);
                        return okHttpCall;
                    }
                });
    }

    private ServiceMethod loadServiceMethod(Method method) {
        //享元设计模式 （做一个缓存）
        ServiceMethod serviceMethod = serviceMethodMapCache.get(method);
        if(null == serviceMethod){
            serviceMethod = new ServiceMethod.Builder(this,method).build();
            serviceMethodMapCache.put(method,serviceMethod);
        }
        return serviceMethod;
    }

    public static class Builder{
        String baseUrl;
        okhttp3.Call.Factory callFactory;
        public Builder baseUrl(String baseUrl){
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder client(okhttp3.Call.Factory callFactory){
            this.callFactory = callFactory;
            return this;
        }

        public Retrofit build() {
            if(null == callFactory){
                callFactory = new OkHttpClient();
            }
            return new Retrofit(this);
        }
    }
}
