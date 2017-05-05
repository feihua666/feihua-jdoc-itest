package com.feihua.doc.core.formater.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.feihua.doc.bean.Api;
import com.feihua.doc.core.context.JDocContext;
import com.feihua.doc.core.constant.Constant;
import com.feihua.doc.core.formater.Formater;
import com.feihua.doc.core.template.FreemarketTemplate;

public class HtmlFormater implements Formater{

    @Override
    public void output(List<Api> apis) {
         String json = JSON.toJSONString(apis, new SerializerFeature[]{SerializerFeature.DisableCircularReferenceDetect});
         Map<Object,Object> paras = new HashMap<Object,Object>();
         paras.put("parser", json);
         FreemarketTemplate.output(Constant.HTML_TEMPALTE, JDocContext.getContext().getjDocConfig().getValue("out.path"), paras);
        
    }

}
