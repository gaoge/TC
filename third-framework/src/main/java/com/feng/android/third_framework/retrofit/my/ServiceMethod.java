package com.feng.android.third_framework.retrofit.my;

import com.google.gson.Gson;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Request;
import okhttp3.ResponseBody;
import timber.log.Timber;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-02 17:57
 * @tips
 */
public class ServiceMethod {
    final Retrofit retrofit;
    final Method method;
    String httpMethod;
    String relativeUrl;
    final ParameterHandler<?>[] parameterHandlers;

    public ServiceMethod(Builder builder){
        this.retrofit = builder.retrofit;
        this.method = builder.method;
        this.httpMethod = builder.httpMethod;
        this.relativeUrl = builder.relativeUrl;
        this.parameterHandlers = builder.parameterHandlers;
    }

    public okhttp3.Call createNewCall(Object[] args) {
        //还需要一个对象，专门用来添加参数
        //添加参数
        RequestBuilder requestBuilder = new RequestBuilder(retrofit.baseUrl,relativeUrl,httpMethod,parameterHandlers,args);



        return retrofit.callFactory.newCall(requestBuilder.build());

    }

    public <T> T parseBody(ResponseBody body) {
        // 获取解析的类型 T 获取方法返回值的类型
        //returnType: Call<UserLoginResult>
        Type returnType = method.getGenericReturnType();
        //returnActualType: UserLoginResult
        Type returnActualType = ((ParameterizedType)returnType).getActualTypeArguments()[0];

        Class<T> dataClass = (Class<T>)returnActualType;
        Gson gson = new Gson();
        T data = gson.fromJson(body.charStream(), dataClass);
        //解析工厂去转换
        return data;
    }

    public static class Builder {
        final Retrofit retrofit;
        final Method method;
        final Annotation[] methodAnnoatations;
        String httpMethod;
        String relativeUrl;
        Annotation[][] parameterAnnotations;
        final ParameterHandler<?>[] parameterHandlers;

        public Builder(Retrofit retrofit, Method method) {
            this.retrofit = retrofit;
            this.method = method;
            methodAnnoatations = method.getAnnotations();
            //参数注解 整体是一个 二维数组
            parameterAnnotations = method.getParameterAnnotations();
            parameterHandlers = new ParameterHandler[parameterAnnotations.length];
        }

        public ServiceMethod build() {
            //解析，okHttp 请求的时候， url = baseUrl + relativeUrl, method,
            for (Annotation annotation : methodAnnoatations){
                //解析Post GET
                parseAnnotationMethod(annotation);
            }

            int count = parameterHandlers.length;
            for(int i=0;i<count;i++){
                Annotation parameter = parameterAnnotations[i][0];
                //Query 等等
                Timber.e("parameter = " + parameter.annotationType().getName());
                //会涉及到  模版模式和策略模式
                if(parameter instanceof Query){
                    //一个一个 封装成ParameterHandler, 不同的参数注解选择不同的策略
                    parameterHandlers[i] = new ParameterHandler.Query<>(((Query)parameter).value());
                }
            }
            return new ServiceMethod(this);
        }

        private void parseAnnotationMethod(Annotation methodAnnotation) {
            if(methodAnnotation instanceof GET){
                parseMethodAndPath("GET",((GET)methodAnnotation).value());
            }else if(methodAnnotation instanceof POST){
                parseMethodAndPath("POST",((GET)methodAnnotation).value());

            }
        }

        private void parseMethodAndPath(String method, String value) {
            this.httpMethod = method;
            this.relativeUrl = value;
        }
    }
}
