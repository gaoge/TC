package com.feng.android.net.engine;

import com.feng.android.net.engine.converter.Converter;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-12 14:07
 * @tips 这里需要新建一个父类IHttpCallBack，让HttpCallBack继承它
 * 否则在反射获取泛型的时候，会获取不到，参见 Utils.analysisClazzInfo()方法
 */
public abstract class HttpCallBack<T> implements EngineCallback {

    @Override
    public void onSuccess(String result) {
        try{
            //做解析
            //不能直接用 Gson 去解析，通过配置去解析
            Converter converter = HttpUtils.mEngineConfig.converter;
            Class<T> resultT = Utils.analysisClazzInfo(this);
            T resultObj = converter.convert(result,resultT);
            onSuccess(resultObj);
        }catch (Exception e){
            //操作异常，解析，访问数据正常，但是拿到结果后操作出错
            e.printStackTrace();
            onFailure(e);
        }


    }
    protected abstract void onSuccess(T resultObj);
}
