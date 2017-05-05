package com.feihua.doc.core.config;

import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import com.feihua.doc.utils.PropUtils;

public class JDocConfig {

    private  Properties properties;

    public  JDocConfig (String configFileName) {
        properties = PropUtils.builderProperties(configFileName);
    };

    public  String getValue(String key) {
        String value = properties.getProperty(key);
        if(StringUtils.isNotBlank(value)){
            value = value.trim();
        }
        return value;
    }

}
