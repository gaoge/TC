package com.feng.android.third_framework.retrofit.my;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-02 18:20
 * @tips
 */
public interface ParameterHandler<T> {
    void apply(RequestBuilder requestBuilder,T value);

    //很多策略，Query策略， Part策略， QueyMap策略，Field策略 等等

    class Query<T> implements ParameterHandler<T>{
        private String key; //保存 就是参数的 key = userName,password

        public Query(String key){
            this.key = key;
        }

        @Override
        public void apply(RequestBuilder requestBuilder,T value) {
            // 添加到request中 // value -> String  更好的是需要一个工厂
            requestBuilder.addQueryName(key,value.toString());

        }
    }

    //还有其它
}
