package com.feihua.doc.core.context;

import com.feihua.doc.bean.Api;
import com.feihua.doc.core.parser.AbstractApiParser;
import com.feihua.doc.core.config.JDocConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yw on 2017/3/30.
 */
public class JDocContext {

    private static JDocContext context = null;

    private JDocConfig jDocConfig;

    private List<Api> apis;

    private AbstractApiParser apiParser;

    private Map<String,Object> dataMap = new HashMap();
    public static JDocContext getContext(){
        if(context == null){
            context = new JDocContext();
        }
        return context;
    }

    public JDocConfig getjDocConfig() {
        return jDocConfig;
    }

    public void setjDocConfig(JDocConfig jDocConfig) {
        this.jDocConfig = jDocConfig;
    }

    public List<Api> getApis() {
        return apis;
    }

    public void setApis(List<Api> apis) {
        this.apis = apis;
    }

    public Map<String, Object> getDataMap() {
        return dataMap;
    }

    public void setDataMap(Map<String, Object> dataMap) {
        this.dataMap = dataMap;
    }

    public AbstractApiParser getApiParser() {
        return apiParser;
    }

    public void setApiParser(AbstractApiParser apiParser) {
        this.apiParser = apiParser;
    }
}
