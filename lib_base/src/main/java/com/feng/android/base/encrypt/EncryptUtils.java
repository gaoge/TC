package com.feng.android.base.encrypt;

import android.text.TextUtils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-29 16:34
 * @tips
 */
public class EncryptUtils {

    public static String md5(String url) {
        if(TextUtils.isEmpty(url)){
            return url;
        }

        StringBuffer sb = new StringBuffer();
        //生成一个MD5加密计算摘要
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");

            //计算md5函数
            messageDigest.update(url.getBytes());
            byte[] cipher = messageDigest.digest();
            for(byte b : cipher){
                //转成了16进制
                String hexStr = Integer.toHexString(b & 0xff);
                //不足还补0
                sb.append(hexStr.length() == 1 ? "0" + hexStr : hexStr);
            }
        //digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的
        //BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return sb.toString();

    }
}
