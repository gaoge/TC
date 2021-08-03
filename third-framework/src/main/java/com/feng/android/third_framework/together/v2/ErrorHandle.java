package com.feng.android.third_framework.together.v2;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-03 15:06
 * @tips
 */
public class ErrorHandle {

    public static class ServerError extends Throwable {
        String errorCode;
        public ServerError(String errorCode, String errorMsg) {
            super(errorMsg);
            this.errorCode = errorCode;
        }
    }
}
