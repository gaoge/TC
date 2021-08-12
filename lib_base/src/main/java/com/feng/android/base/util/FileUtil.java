package com.feng.android.base.util;

import java.io.File;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-12 13:33
 * @tips
 */
public class FileUtil {

    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     *
     * @param dir 将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful. If a
     * deletion fails, the method stops attempting to delete and returns
     * "false".
     */
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            for (File file : files) {
                file.delete();
            }
        }
        // 目录此时为空，可以删除
        return true;
    }
}
