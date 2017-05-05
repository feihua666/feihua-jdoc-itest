package com.feihua.doc.utils;

import java.util.List;

/**
 * Created by yw on 2017/3/30.
 */
public class JDocUtils {

    public static void javadocExcute(List<String> files,String classname){
        /**
         * -doclet执行把所有的文档注释放到DocCoreHandler参数里，调用start方法
         */
        files.add(0, "-doclet");
        files.add(1, classname);

        String[] docArgs = files.toArray(new String[files.size()]);

        com.sun.tools.javadoc.Main.execute(docArgs);
    }
}
