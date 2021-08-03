package com.feng.android.third_framework.retrofit.ssl;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-03 18:30
 * @tips
 */
public class TCSSLSocketFactory {

    private static X509TrustManager trustManager;

    static {
        trustManager = new HTTPSTrustManager();
    }

    public static X509TrustManager getTrustManager() {
        return trustManager;
    }

    public static SSLSocketFactory getTrustAllSockeFactory(){
        try {
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, new X509TrustManager[]{trustManager} , new SecureRandom());
            return context.getSocketFactory();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        return null;
    }


    static class HTTPSTrustManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] certificates, String authType) throws CertificateException {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] certificates, String authType) throws CertificateException {

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[]{};
        }
    }
}
