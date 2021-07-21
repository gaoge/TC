package com.feng.android.designpattern.proxy_pattern.retrofit;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-21 13:47
 * @tips
 */
public class GGRetrofit {

    public <T> T create(Class<T> clazz){
        return (T) Proxy.newProxyInstance(
                clazz.getClassLoader(),
                new Class<?>[]{clazz},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                        //如果要实现Retrofit 一样的代码应该怎么半？
                        //1.解析方法的所有注解 比如 @POST，@GET，@FormUrlEncoded等等

                        //2.解析参数的所有注解 比如 FiledMap Part PartMap等等

                        //3. 封装成一个Call
                        return "返回";
                    }
                }
        );
    }
}
