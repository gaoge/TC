package com.feng.android.base.fixBug;

import android.content.Context;

import com.feng.android.base.util.ArrayUtil;
import com.feng.android.base.util.FileUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import dalvik.system.BaseDexClassLoader;
import timber.log.Timber;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-12 17:49
 * @tips
 */
public class FixDexManager {

    private Context mContext;
    private File mDexDir;

    public FixDexManager(Context context) {
        this.mContext = context;
        //应用可以访问的 dex 目录
        this.mDexDir = mContext.getDir("odex",Context.MODE_PRIVATE);
    }

    /**
     * 修复 dex 包
     * @param fixDexPath
     */
    public void fixDex(String fixDexPath) throws Exception {

        //2. 获取下载好的补丁的 dexElement
        //2.1 移动到系统能够访问的 dex 目录下
        File srcFile = new File(fixDexPath);
        if(!srcFile.exists()){
            throw new FileNotFoundException(fixDexPath);
        }
        //applicationId/app_odex/fix.dex
        File destFile = new File(mDexDir,srcFile.getName());
        if(destFile.exists()){
            FileUtil.copyFile(srcFile,destFile);
            return;
        }


        //2.2 classLoader 读取 fixDex 路径 ,为什么加入到集合？ 因为一启动可能就要修复
        List<File> fixDexFiles = new ArrayList<>();
        fixDexFiles.add(destFile);

        fixDexFiles(fixDexFiles);

    }

    private void fixDexFiles(List<File> fixDexFiles) throws Exception {
        //1. 先获取运行的 dexElement
        ClassLoader applicationClassLoader = mContext.getClassLoader();
        Object applicationDexElements = getDexElementsByClassLoader(applicationClassLoader);

        File optimizeDirectory = new File(mDexDir,"odex");
        if(!optimizeDirectory.exists()){
            optimizeDirectory.mkdirs();
        }
        //修复
        for (File fixDexFile : fixDexFiles) {
            //dexPath dex路径
            //optimizeDirectory 解压路径
            //libraryPath .so文件位置
            //parent 父Classloader
            ClassLoader fixDexClassLoader = new BaseDexClassLoader(
                    fixDexFile.getAbsolutePath(),//dex 路径 必须要在应用目录下的odex 文件中
                    optimizeDirectory,//解压路径
                    null,
                    applicationClassLoader
            );

            Object fixDexElements = getDexElementsByClassLoader(fixDexClassLoader);

            //3. 把补丁的dexElement ，插到已运行的 dexElement 的前边,数组合并
            applicationDexElements = ArrayUtil.combineArray(fixDexElements, applicationDexElements);

            //4. 把合并后的数据注入到原来的classLoader中

            injectDexElements(applicationClassLoader, applicationDexElements);
        }
    }

    private void injectDexElements(ClassLoader classLoader, Object dexElements) throws Exception {
        //1. 先获取 dexPathList
        Field pathListField = BaseDexClassLoader.class.getDeclaredField("pathList");
        pathListField.setAccessible(true);
        Object pathList = pathListField.get(classLoader);

        //2. 获取pathList里面的 dexElements
        Field dexElementsField = pathList.getClass().getDeclaredField("dexElements");
        dexElementsField.setAccessible(true);
        dexElementsField.set(pathList,dexElements);

    }

    /**
     * 从 classLoader 中获取 dexElements
     * @param classLoader
     * @return
     */
    private Object getDexElementsByClassLoader(ClassLoader classLoader) throws Exception {
        //1. 先获取 dexPathList
        Field pathListField = BaseDexClassLoader.class.getDeclaredField("pathList");
        pathListField.setAccessible(true);
        Object pathList = pathListField.get(classLoader);

        //2. 获取pathList里面的 dexElements
        Field dexElementsField = pathList.getClass().getDeclaredField("dexElements");
        dexElementsField.setAccessible(true);
        Object dexElements = dexElementsField.get(pathList);
        return dexElements;
    }

    /**
     * 加载全部的修复包
     */
    public void loadFixDex() throws Exception {
        File[] fixDexFiles = mDexDir.listFiles();
        List<File> fixDexFilesList = new ArrayList<>();

        for (File fixDexFile : fixDexFiles) {
            if(fixDexFile.getName().endsWith(".dex")){
                fixDexFilesList.add(fixDexFile);
            }
        }

        fixDexFiles(fixDexFilesList);
    }
}
