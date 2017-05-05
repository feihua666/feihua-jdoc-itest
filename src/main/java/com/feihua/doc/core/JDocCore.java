package com.feihua.doc.core;

import java.util.List;

import com.feihua.doc.core.parser.AbstractApiParser;
import com.feihua.doc.core.parser.ApiParserBuilder;
import com.feihua.doc.core.config.JDocConfig;
import com.feihua.doc.core.context.JDocContext;
import com.feihua.doc.core.exception.JDocException;
import com.feihua.doc.utils.FileUtils;
import com.feihua.doc.utils.JDocUtils;
import org.apache.commons.lang3.StringUtils;

public class JDocCore {
    
    private String configFileName;
    
    
    public void build(){
        /**
         * 初始化加载配置文件
         */
        if(configFileName == null){
            configFileName = "jdoc.properties";
        }
        JDocContext.getContext().setjDocConfig(new JDocConfig(configFileName));
        /**
         * 获取扫描的包名
         */
        String packageName = JDocContext.getContext().getjDocConfig().getValue("package.name");
        
        if(StringUtils.isBlank(packageName)){
            throw new JDocException("package.name para is not allow empty");
        }
        List<String> files = FileUtils.getJavaFileNameByPackage(packageName);

        AbstractApiParser abstractApiParser = ApiParserBuilder.getParser();
        files = abstractApiParser.filterController(files);
        JDocContext.getContext().setApiParser(abstractApiParser);
        JDocUtils.javadocExcute(files, abstractApiParser.getClass().getName());
    }
    
    
    public JDocCore setConfigFileName(String configFileName) {
        this.configFileName = configFileName;
        return this;
    }

    
    public String getConfigFileName() {
        return configFileName;
    }
    
    

}
