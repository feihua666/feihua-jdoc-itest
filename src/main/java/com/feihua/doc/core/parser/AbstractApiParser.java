package com.feihua.doc.core.parser;

import java.util.ArrayList;
import java.util.List;

import com.feihua.doc.bean.Api;
import com.feihua.doc.bean.ApiRequestParam;
import com.feihua.doc.core.context.JDocContext;
import com.feihua.doc.core.formater.Formater;
import com.feihua.doc.core.formater.FormaterBuilder;
import com.sun.javadoc.RootDoc;
import org.apache.commons.lang3.StringUtils;

import com.feihua.doc.bean.ApiResponseParam;
import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.Tag;

public  abstract class AbstractApiParser{

    private ClassDoc[] classDocs;

    public static boolean start(RootDoc root) {
        JDocContext.getContext().getApiParser().setClassDocs(root.classes());
        FormaterBuilder.getFormater().output(JDocContext.getContext().getApiParser().generateApi(root.classes()));
        return true;
    }

    /**
     * 过滤出Controller文档
     * @param fileNames
     * @return
     */
    public abstract List<String> filterController(List<String> fileNames);
    
    
    public abstract List<Api> generateApi(ClassDoc[] classDocs);
    
    public List<ApiRequestParam> generateApiReqeustParam(Tag[] tags){
        List<ApiRequestParam> apiRequestParams = new ArrayList<ApiRequestParam>();
        List<Tag> paramTags = getTagsByTag(tags,"@param");
        paramTags.addAll(getTagsByTag(tags,"@requParam"));
        for(Tag tag:paramTags){
            ApiRequestParam apiRequestParam = new ApiRequestParam();
            String tagText = tag.text();
            String [] descs = null;
            if(tagText.indexOf("|") > 0){
                descs = tagText.split("\\|");
            }else {
                descs = tagText.split(" ");
            }

            /*if(descs.length<4){
                throw new JDocException("the param formater dose not match jdoc formater "+tagText);
            }*/
            if(descs.length == 1){
                apiRequestParam.setName(descs[0]);
            }else if(descs.length == 2){
                apiRequestParam.setName(descs[0]);
                apiRequestParam.setDesc(descs[1]);
            }else if(descs.length == 3){
                apiRequestParam.setName(descs[0]);
                apiRequestParam.setDesc(descs[1]);
                apiRequestParam.setRequired(descs[2]);
            }else if(descs.length == 4){
                apiRequestParam.setName(descs[0]);
                apiRequestParam.setDesc(descs[1]);
                apiRequestParam.setRequired(descs[2]);
                apiRequestParam.setType(descs[3]);
            }

            apiRequestParams.add(apiRequestParam);
        }
        return apiRequestParams;
    }
    
    public List<ApiResponseParam> generateApiResponseParam(Tag[] tags){
        List<ApiResponseParam> apiResponseParams = new ArrayList<ApiResponseParam>();
        List<Tag> paramTags = getTagsByTag(tags,"@respParam");
        for(Tag tag:paramTags){
            ApiResponseParam apiResponseParam = new ApiResponseParam();
            String tagText = tag.text();
            String [] descs= tagText.split("\\|");
            /*if(descs.length<4){
                throw new JDocException("the param formater dose not match jdoc formater"+tagText);
            }*/
            if(descs.length == 1){
                apiResponseParam.setName(descs[0]);
            }else if(descs.length == 2){
                apiResponseParam.setName(descs[0]);
                apiResponseParam.setDesc(descs[1]);
            }if(descs.length == 3){
                apiResponseParam.setName(descs[0]);
                apiResponseParam.setDesc(descs[1]);
                apiResponseParam.setType(descs[2]);
            }if(descs.length == 4){
                apiResponseParam.setName(descs[0]);
                apiResponseParam.setDesc(descs[1]);
                apiResponseParam.setType(descs[2]);
                apiResponseParam.setRequired(descs[3]);
            }

            
            apiResponseParams.add(apiResponseParam);
        }
        return apiResponseParams;
    }
    
    public String getTitle(Tag[] tags){
        List<Tag> targetTags = getTagsByTag(tags,"@title");
        if(targetTags!=null&&!targetTags.isEmpty()){
            return targetTags.get(0).text();
        };
        
        return "";
    }
    
    public String getRespBody(Tag[] tags){
        List<Tag> targetTags = getTagsByTag(tags,"@respBody");
        if(targetTags!=null&&!targetTags.isEmpty()){
            return targetTags.get(0).text();
        };
        
        return "";
    }

    /**
     * 默认object
     * @param tags
     * @return
     */
    public String getRespDataType(Tag[] tags){
        List<Tag> targetTags = getTagsByTag(tags,"@respDataType");
        if(targetTags!=null&&!targetTags.isEmpty()){
            return targetTags.get(0).text();
        };

        return "object";
    }
    public List<Tag> getTagsByTag(Tag[] tags,String tagName){
        List<Tag> targetTags = new ArrayList<Tag>();
        for(Tag tag:tags){
           if(StringUtils.equals(tag.name(),tagName)){
               targetTags.add(tag);
           };
        }
        return targetTags;
    }

    public ClassDoc[] getClassDocs() {
        return classDocs;
    }

    public void setClassDocs(ClassDoc[] classDocs) {
        this.classDocs = classDocs;
    }
}
