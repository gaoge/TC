package com.feng.android.net.engine;

import com.feng.android.net.engine.converter.Converter;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-04 14:33
 * @tips
 */
public class EngineConfig {
    final IHttpStrategy engineStrategy;
    final Converter converter;

    public EngineConfig(Builder builder) {
        this.engineStrategy = builder.engineRequest;
        this.converter = builder.converter;
    }

    public static class Builder {
        public IHttpStrategy engineRequest;
        Converter converter;

        public Builder converter(Converter converter){
            this.converter = converter;
            return this;
        }

        public Builder engineStrategy(IHttpStrategy engineRequest){
            this.engineRequest = engineRequest;
            return this;
        }
        
        public EngineConfig build(){
            //如果上层不配置，返回默认的
            if(null == converter){
                converter = Converter.DEFAULT_CONVERTER;
            }
            return new EngineConfig(this);
        }
    }
}
