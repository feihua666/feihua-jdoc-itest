package com.feihua.doc.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.feihua.doc.core.constant.Constant;

public class FileUtils {

   

    /**
     * 通过包名获取文件
     * 
     * @param packageName
     * @return
     */
    public static List<String> getJavaFileNameByPackage(String packageName) {
        return getJavaFileNamesByPath(getJavaFileNamePathByPackage(packageName));
    }

    public static String getJavaFileNamePathByPackage(String packageName){
        String path = Constant.JAVA_FILE_PATH + packageName.replaceAll("\\.", "/");
        return path;
    }
    /**
     * 通过路径获取所有的java文件
     * 
     * @param path
     * @return
     */
    public static List<String> getJavaFileNamesByPath(String path) {
        List<String> javaFileNames = new ArrayList<String>();
        File rootFile = new File(path);
        if (rootFile.isFile()) {
            if (rootFile.getName().lastIndexOf(Constant.JAVA_FILE_SUFFIX) > 0) {
                javaFileNames.add(rootFile.getAbsolutePath());
            }
        } else {
            File[] files = rootFile.listFiles();
            if (files != null) {
                for (File file : files) {
                    javaFileNames.addAll(getJavaFileNamesByPath(file.getPath()));
                }
            }
        }
        return javaFileNames;
    }

    public static void main(String[] args) {
       List<String> fileName = FileUtils.getJavaFileNameByPackage("com.nmtx.doc");
       System.out.println(fileName.size());
    }

}
