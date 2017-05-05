package com.feihua.doc.core.constant;

import com.feihua.doc.core.context.JDocContext;

public class Constant {
   public static final String DEFAULT_CHARSET = "utf-8";

   public static final String POST = "post";
   
   public static final String GET="get";
   
   public static final String HTML_TEMPALTE = "jdoc_template.ftl";
   
   public static final String SPRING_MVC="springmvc";
   
   public static final String JAVA_FILE_SUFFIX=".java";

   public static final String ITEST_FORMATER="itest";

   public static final String JAVA_FILE_PATH = (System.getProperty("user.dir") +"/"+ JDocContext.getContext().getjDocConfig().getValue("java.source")+"/");
   
  
}
