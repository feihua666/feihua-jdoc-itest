package com.feihua.doc.utils;


public class PathUtils {
   public static String getRootClassPath(Object object){
       String rootPath =  object.getClass().getResource("/").getPath();
       return rootPath.substring(1,rootPath.length());
   }
}
