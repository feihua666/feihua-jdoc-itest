package com.feihua.doc.core.formater.impl;

import com.alibaba.fastjson.JSON;
import com.feihua.doc.bean.Api;
import com.feihua.doc.bean.ApiRequestParam;
import com.feihua.doc.bean.ApiResponseParam;
import com.feihua.doc.core.constant.Constant;
import com.feihua.doc.core.context.JDocContext;
import com.feihua.doc.utils.FileUtils;
import com.feihua.doc.utils.JDocUtils;
import com.feihua.doc.bean.ApiAction;
import com.feihua.doc.core.formater.Formater;
import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.FieldDoc;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.RootDoc;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.List;

public class ItestFormater implements Formater{

    public static String [] JAVA_OBJECT_TYPE = {"string","date"};
    public static String [] JAVA_COLLECTION_TYPE = {"list","set"};
    @Override
    public void output(List<Api> apis) {
         for(Api api:apis){
             for(ApiAction apiAction:api.getApiActions()){
                 String respText = apiAction.getRespText();
                 if(respText == null || "".equals(respText)){
                     continue;
                 }
                 try {
                     Class.forName(respText).newInstance();
                     List<String> files = FileUtils.getJavaFileNamesByPath(FileUtils.getJavaFileNamePathByPackage(respText )+ Constant.JAVA_FILE_SUFFIX);
                     if(!CollectionUtils.isEmpty(files)){
                         String fileName = files.get(0);
                         String packageFileName = fileName.substring(Constant.JAVA_FILE_PATH.length(), fileName.length() - Constant.JAVA_FILE_SUFFIX.length()).replace(File.separator, ".");
                         List<ApiResponseParam> result = assembly(packageFileName,null);
                         if(!CollectionUtils.isEmpty(result)){
                             List<ApiResponseParam> rresult = new ArrayList<>();
                             for(ApiResponseParam responseParam:result){
                                 boolean exist = false;
                                 for(ApiResponseParam rresponseParam:rresult){
                                     if(responseParam.getParentId() != null && rresponseParam.getParentId() != null &&rresponseParam.getParentId().equals(responseParam.getParentId()) && rresponseParam.getName().equals(responseParam.getName())){
                                         exist = true;break;
                                     }
                                 }
                                 if(!exist){
                                     rresult.add(responseParam);
                                 }
                             }

                             apiAction.setRespParams(rresult);
                         }
                     }
                 } catch (Exception e) {
                 }
             }
         }

        System.out.println();
        List<Map<String,Object>> pages = new ArrayList<>();
        for(Api api:apis) {
            Map<String, Object> page = new HashMap<>();
            page.put("name", api.getTitle());
            List<Map<String, Object>> actions = new ArrayList<>();
            page.put("actionDtos", actions);
            for (ApiAction apiAction : api.getApiActions()) {
                Map<String, Object> action = new HashMap<>();
                action.put("name", StringUtils.isEmpty(apiAction.getTitle()) ? apiAction.getDesc() : apiAction.getTitle());
                action.put("requestType", apiAction.getRequestType().split(",")[0]);
                action.put("description", apiAction.getDesc());
                action.put("requestUrl", apiAction.getUrl());
                action.put("responseDataType", "object".equals(apiAction.getRespDataType()) ? "1":"0");
                //action.put("mockDataRule", apiAction.getRespText());
                actions.add(action);
                List<Map<String, Object>> requestParameters = new ArrayList<>();
                action.put("requestParametersDtos", requestParameters);
                for (ApiRequestParam apiRequestParam : apiAction.getReqParams()) {
                    Map<String, Object> requestParameter = new HashMap<>();
                    requestParameter.put("name", apiRequestParam.getDesc());
                    requestParameter.put("identifier", apiRequestParam.getName());
                    requestParameter.put("isRequred", "必填".equals(apiRequestParam.getRequired()) ? "1":"0");
                    requestParameter.put("dataType", getDataType(apiRequestParam.getType()));

                    requestParameters.add(requestParameter);
                }
                List<Map<String, Object>> responseParameters = new ArrayList<>();
                for(ApiResponseParam responseParam:apiAction.getRespParams()){
                    Map<String,Object> responseParameter = new HashMap<>();
                    responseParameter.put("name",responseParam.getDesc());
                    responseParameter.put("identifier",responseParam.getName());
                    responseParameter.put("dataType",getDataType(responseParam.getType()));
                    responseParameter.put("id",responseParam.getId());
                    responseParameter.put("parentId",responseParam.getParentId());

                    responseParameters.add(responseParameter);
                }
                action.put("responseParametersDtos",responseParameters);
            }
            pages.add(page);
        }
        System.out.println(JSON.toJSONString(pages,true));

        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable tText = new StringSelection(JSON.toJSONString(pages,true));
        clip.setContents(tText, null);

        System.out.println("*********完成，已复制到剪切版，你可以直接粘贴到文本编辑器*********");
    }

    private List<ApiResponseParam> assembly(String className,String parentId){

        List<ApiResponseParam> result = new ArrayList<>();
        try{
            Class clazz = Class.forName(className);
            for(;clazz != null &&clazz != Object.class;clazz = clazz.getSuperclass()){
                List<String> files = FileUtils.getJavaFileNamesByPath(FileUtils.getJavaFileNamePathByPackage(clazz.getName().replace("class","").trim() )+ Constant.JAVA_FILE_SUFFIX);
                if(!CollectionUtils.isEmpty(files)){
                    JDocUtils.javadocExcute(files,this.getClass().getName());
                    ClassDoc classDoc = (ClassDoc) JDocContext.getContext().getDataMap().get("classDoc");
                    List<FieldDoc> fieldDocs = (List<FieldDoc>) JDocContext.getContext().getDataMap().get("fieldDocs");

                    for(FieldDoc fieldDoc:fieldDocs){
                        String fieldName = fieldDoc.name();
                        String comment = fieldDoc.commentText();

                        ApiResponseParam responseParam = new ApiResponseParam();
                        if(JDocContext.getContext().getDataMap().get("idIncre") == null){
                            JDocContext.getContext().getDataMap().put("idIncre",1);
                        }else {
                            JDocContext.getContext().getDataMap().put("idIncre",(int)(JDocContext.getContext().getDataMap().get("idIncre")) + 1);
                        }
                        responseParam.setId(JDocContext.getContext().getDataMap().get("idIncre").toString());
                        responseParam.setName(fieldName);
                        responseParam.setType(fieldDoc.type().simpleTypeName());
                        responseParam.setDesc(comment);
                        responseParam.setParentId(parentId);
                        result.add(responseParam);
                        if(StringUtils.endsWithAny(fieldDoc.type().qualifiedTypeName().toLowerCase(),JAVA_COLLECTION_TYPE)){
                            List<ApiResponseParam> result1 = assembly(getGenericTypeClassName(fieldName,classDoc),responseParam.getId());
                            if(!CollectionUtils.isEmpty(result1)){
                                result.addAll(result1);
                            }

                        }
                    }

                    JDocContext.getContext().getDataMap().remove("classDoc");
                    JDocContext.getContext().getDataMap().remove("fieldDocs");
                }

            }
        }catch (Exception e){}
        return result;
    }
    /**
     * 主要处理响应bean
     * @param root
     * @return
     */
    public static boolean start(RootDoc root) {

        List<FieldDoc> result = new ArrayList<>();
        ClassDoc[] classDocs = root.classes();
         ClassDoc classDoc = classDocs[0];
            for (FieldDoc fieldDoc : classDoc.fields(false)) {
                String fieldName = fieldDoc.name();
                String comment = fieldDoc.commentText();
                for(MethodDoc methodDoc:classDoc.methods(false)){
                    if(methodDoc.name().equalsIgnoreCase("get"+fieldName) || methodDoc.name().equalsIgnoreCase("is"+fieldName)){
                        /*ApiResponseParam responseParam = new ApiResponseParam();
                        responseParam.setId(UUID.randomUUID().toString());
                        responseParam.setName(fieldName);
                        responseParam.setType(fieldDoc.type().simpleTypeName());
                        responseParam.setDesc(comment);
                        result.add(responseParam);*/
                        result.add(fieldDoc);
                    }
                }
            }

        JDocContext.getContext().getDataMap().put("classDoc",classDoc);
        JDocContext.getContext().getDataMap().put("fieldDocs",result);
        return true;
    }
    private static String getGenericTypeClassName(String fieldName,ClassDoc classDoc){
        try {
            for(Field field:Class.forName(classDoc.qualifiedTypeName()).getDeclaredFields()){
                if(field.getName().equals(fieldName)){
                    if(field.getGenericType() != null && ((ParameterizedType)field.getGenericType()).getActualTypeArguments() != null){

                        return (((ParameterizedType)field.getGenericType()).getActualTypeArguments())[0].toString().replace("class","").trim();

                    }


                }
            }
        } catch (ClassNotFoundException e) {

        }
        return null;
    }

    private String getDataType(String raw){
        String result = null;
        if(!StringUtils.isEmpty(raw))
        switch (raw.toLowerCase()){
            case "integer":
            case "int":
            case "float":
            case "long":
            case "short":
            case "double":
            case "number":
                result = "number";
                break;
            case "string":
            case "char":
            case "date":
                result = "string";
                break;
            case "list":
            case "set":
            case "array[object]":
                result = "array[object]";
                break;
                default:
                    result = "object";
        }
        return result;
    }
}
