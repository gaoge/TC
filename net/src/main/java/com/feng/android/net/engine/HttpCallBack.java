package com.feng.android.net.engine;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-12 14:07
 * @tips 这里需要新建一个父类IHttpCallBack，让HttpCallBack继承它
 * 否则在反射获取泛型的时候，会获取不到，参见 Utils.analysisClazzInfo()方法
 */
public abstract class HttpCallBack<T> implements IHttpCallBack<T> {

}
