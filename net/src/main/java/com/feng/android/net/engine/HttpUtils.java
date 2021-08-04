package com.feng.android.net.engine;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-12 09:57
 * @tips
 */
public class HttpUtils {
    private IHttpRequest mHttpRequest;
    private static IHttpRequest mInitHttpRequest;
    private final int TYPE_POST = 0x0011,TYPE_GET = 0x0022;
    private int mType = TYPE_GET;
    private Map<String,Object> mParams;
    private String mUrl;
    private Context mContext;

    public static HttpUtils with(Context contxt){
        return new HttpUtils(contxt);
    }

    public static void initHttpRequest(IHttpRequest httpRequest){
        mInitHttpRequest = httpRequest;
    }

    public HttpUtils httpRequest(IHttpRequest httpRequest){
        mHttpRequest = httpRequest;
        return this;
    }

    private HttpUtils(Context context){
//        mHttpRequest = new OKHttpRequest();
        mParams = new HashMap<>();
        this.mContext = context;
    }

    public <T> void request(){
        request(null);
    }

    public <T> void request(final HttpCallBack<T> callBack){
        if(mHttpRequest == null){
            mHttpRequest = mInitHttpRequest;
        }
        //异常判断 mUrl非空等
        if(mType == TYPE_GET){
            mHttpRequest.get(mContext,mUrl,mParams,callBack,true);
        }else if(mType == TYPE_POST){
            mHttpRequest.post(mContext,mUrl,mParams,callBack,false);
        }
    }

    public HttpUtils get(){
        mType = TYPE_GET;
        return this;
    }

    public HttpUtils post(){
        mType = TYPE_POST;
        return this;
    }


    public HttpUtils param(String key,Object value){
        mParams.put(key,value);
        return this;
    }

    public HttpUtils url(String url){
        this.mUrl = url;
        return this;
    }

    public HttpUtils cache(boolean cache){
        return this;
    }



    //可能10几个参数
    public <T> void get(Context context, String url, Map<String,Object> params, final HttpCallBack<T> callBack, final boolean cache ){
        mHttpRequest.get(context,url,params,callBack,cache);


    }




}
