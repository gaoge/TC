package com.feng.android.third_framework.retrofit.v3;

import com.feng.android.lib_framework.net.entity.Result;
import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-02 16:03
 * @tips
 */
public abstract class HttpCallback<T> implements Callback<Result<T>> {
    @Override
    public void onResponse(Call<Result<T>> call, Response<Result<T>> response) {
        Result<T> result = response.body();
        
        //在这里处理 错误的body
        if(null == result){
            //处理错误情况，像 401 等等
            response.errorBody();
        }
        
        if(!result.isOk()){
            onError(result.getCode(),result.getMsg());
            return;
        }
        //解析，获取类上面的泛型
        Class<T> dataClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        Gson gson = new Gson();
        T data = gson.fromJson(result.data.toString(),dataClass);
        onSucceed(data);
    }

    @Override
    public void onFailure(Call<Result<T>> call, Throwable t) {
        //处理失败，联网，解析出错，自己弄一弄
        Timber.e(t.getMessage());

    }

    public abstract void onSucceed(T result);
    public abstract void onError(String code,String msg);
}
