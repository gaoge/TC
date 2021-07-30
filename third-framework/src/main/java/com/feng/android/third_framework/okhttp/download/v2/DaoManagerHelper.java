package com.feng.android.third_framework.okhttp.download.v2;

import android.content.Context;

import com.feng.android.third_framework.okhttp.download.v2.db.DownloadEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-30 10:39
 * @tips
 */
class DaoManagerHelper {

    private static DaoManagerHelper sDaoHelper = new DaoManagerHelper();

    private DaoManagerHelper(){

    }

    public static DaoManagerHelper getManager() {
        return sDaoHelper;
    }

    public List<DownloadEntity> queryAll(String mUrl) {
        return new ArrayList<DownloadEntity>();
    }

    public void addEntity(DownloadEntity downloadEntity) {
    }

    public void init(Context context) {
    }
}
