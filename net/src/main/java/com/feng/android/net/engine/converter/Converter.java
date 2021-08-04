package com.feng.android.net.engine.converter;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-04 14:26
 * @tips
 */
public interface Converter {

    Converter DEFAULT_CONVERTER = new Converter() {
        @Override
        public <T> T convert(String value, Class<T> type) {
            return (T) value;
        }
    };

    <T> T convert(String value, Class<T> type) ;
}
