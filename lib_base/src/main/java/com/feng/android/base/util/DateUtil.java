package com.feng.android.base.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-12 13:31
 * @tips
 */
public class DateUtil {

    /**
     * 返回当前日期根据格式
     **/
    public static String getAssignTime(String dateFormatStr) {
        DateFormat dataFormat = new SimpleDateFormat(dateFormatStr);
        long currentTime = System.currentTimeMillis();
        return dataFormat.format(currentTime);
    }
}
