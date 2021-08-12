package com.feng.android.lib_framework.net.error;

import java.net.UnknownServiceException;

import javax.net.ssl.SSLHandshakeException;

import retrofit2.HttpException;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-03 15:06
 * @tips
 */
public class ErrorHandle {

    public static class ServerError extends Throwable {
        public String errorMsg;
        public String errorCode;
        public ServerError(String errorCode, String errorMsg) {
            this.errorMsg = errorMsg;
            this.errorCode = errorCode;
        }
    }

    public static class HttpError extends Throwable{

        private static final int BAD_REQUEST = 400;
        private static final int UNAUTHORIZED = 401;
        private static final int FORBIDDEN = 403;
        private static final int NOT_FOUND = 404;
        private static final int REQUEST_TIMEOUT = 408;
        private static final int INTERNAL_SERVER_ERROR = 500;
        private static final int BAD_GATEWAY = 502;
        private static final int SERVICE_UNAVAILABLE = 503;
        private static final int GATEWAY_TIMEOUT = 504;

        public String errorMessage = "未知错误";

        public HttpError(Throwable throwable) {
            if(throwable instanceof HttpException){
                HttpException httpException = (HttpException) throwable;
                switch (httpException.code()){
                    case NOT_FOUND:
                        this.errorMessage = "资源未找到";
                        break;
                }
            }else if(throwable instanceof UnknownServiceException){
                this.errorMessage = "不提供服务";
            }else if(throwable instanceof SSLHandshakeException){
                this.errorMessage = "证书验证错误";
            }
        }
    }
}
