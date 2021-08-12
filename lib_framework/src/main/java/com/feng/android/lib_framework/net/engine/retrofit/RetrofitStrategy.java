package com.feng.android.lib_framework.net.engine.retrofit;

import android.content.Context;

import com.feng.android.lib_framework.net.engine.EngineCallback;
import com.feng.android.lib_framework.net.engine.IHttpStrategy;

import java.io.IOException;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-04 13:14
 * @tips
 */
public class RetrofitStrategy implements IHttpStrategy {
    @Override
    public void get(Context context, String url, Map<String, Object> params, EngineCallback callBack, boolean cache) {
        //1. 第一个要解决的问题就是 url 是作为一个参数，但是Retrofit 是注解
        //2. 返回值应该怎么处理 ？ 不应该直接指定泛型对象，只能通用

        //解决思路: 1.POST() 和 GET() 注解不能用 url 作为注解参数,用 @Url 代替它

        Call call = RetrofitClient.getServiceApi().getMethod(url,params);
        executeCall(callBack, call);
    }

    private void executeCall(EngineCallback callBack, Call call) {
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //成功解析即可
                ResponseBody body = response.body();
                if(body == null){
                    //401
                    body = response.errorBody();
                }

//                //解析 v1 ，写死 Gson 转换
//                Class<T> result = Utils.analysisClazzInfo(callBack);
//                Gson gson = new Gson();
//                T data = gson.fromJson(body.charStream(), result);
//                callBack.onSuccess(data);

                //v2 ,添加 converter,动态转换
                try {
                    callBack.onSuccess(body.string());
                } catch (IOException e) {
                    e.printStackTrace();
                    callBack.onFailure(e);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callBack.onFailure(new RetrofitErrorException(t.getMessage()));
            }
        });
    }

    @Override
    public  void post(Context context, String url, Map<String, Object> params, EngineCallback callBack, boolean cache) {
        Call call = RetrofitClient.getServiceApi().postMethod(url,params);
        executeCall(callBack, call);
    }

    @Override
    public void download(Context context, String url, Map<String, Object> params, EngineCallback callBack) {

    }

    @Override
    public void upload(Context context, String url, Map<String, Object> params,EngineCallback callBack) {

    }
}
