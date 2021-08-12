package com.feng.android.net.engine;

import android.app.Application;

import com.feng.android.net.engine.converter.GsonConvert;
import com.feng.android.net.engine.retrofit.RetrofitStrategy;

import org.xutils.x;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-04 10:06
 * @tips
 */
public class HttpEngineFacade {

    public static void init(Application application){

        PreferenceUtil.getInstance().init(application);
        x.Ext.init(application);

        //配置
        EngineConfig config = new EngineConfig.Builder()
                .engineStrategy(new RetrofitStrategy())
                //添加解析工厂 Gson Xml
                .converter(new GsonConvert())
                //添加默认参数，
                .build();
        HttpUtils.initConfig(config);
    }
}
