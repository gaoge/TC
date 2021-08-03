package com.feng.android.third_framework.retrofit.my;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-03 09:37
 * @tips
 */
public interface Callback<T> {
    void onResponse(Call<T> call,Response<T> response);
    void onFailure(Call<T> call,Throwable t);
}
