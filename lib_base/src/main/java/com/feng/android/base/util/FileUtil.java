package com.feng.android.base.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

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

    /**
     * 拷贝文件
     * @param src
     * @param dest
     * @throws IOException
     */
    public static void copyFile(File src,File dest) throws IOException{
        FileChannel inChannel = null;
        FileChannel outChannel = null;

        try{
            if(!dest.exists()){
                dest.createNewFile();
            }
            inChannel = new FileInputStream(src).getChannel();
            outChannel = new FileOutputStream(dest).getChannel();
            inChannel.transferTo(0,inChannel.size(),outChannel);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(null != inChannel){
                inChannel.close();
            }
            if(null != outChannel){
                outChannel.close();
            }
        }
    }
}
