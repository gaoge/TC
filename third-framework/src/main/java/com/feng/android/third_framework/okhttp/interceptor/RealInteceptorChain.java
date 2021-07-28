package com.feng.android.third_framework.okhttp.interceptor;

import com.feng.android.third_framework.okhttp.Request;
import com.feng.android.third_framework.okhttp.Response;

import java.io.IOException;
import java.util.List;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-28 10:40
 * @tips
 */
public class RealInteceptorChain implements Interceptor.Chain {
    final List<Interceptor> interceptors;
    final int index;
    final Request request;

    public RealInteceptorChain(List<Interceptor> interceptors,int index,Request request) {
        this.interceptors = interceptors;
        this.index = index;
        this.request = request;
    }

    @Override
    public Request request() {
        return request;
    }

    @Override
    public Response proceed(Request request) throws IOException {
        RealInteceptorChain next = new RealInteceptorChain(interceptors,index+1,request);
        Interceptor interceptor = interceptors.get(index);
        Response response = interceptor.intercept(next);
        return response;
    }
}
