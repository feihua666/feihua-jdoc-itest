package com.feihua.doc.core.formater;

import com.feihua.doc.core.context.JDocContext;
import com.feihua.doc.core.formater.impl.ItestFormater;
import org.apache.commons.lang3.StringUtils;

import com.feihua.doc.core.constant.Constant;
import com.feihua.doc.core.formater.impl.HtmlFormater;

public class FormaterBuilder{
    
    private static Formater formater;

    public static Formater getFormater(){
        if(formater == null){
            String apiParserName = JDocContext.getContext().getjDocConfig().getValue("parser.formater");

            if(StringUtils.equals(apiParserName, Constant.ITEST_FORMATER)){
                formater = new ItestFormater();
            }
            if(formater==null){
                formater = new ItestFormater();
            }
        }
        return formater;
        
    } 
    
    

}
