package com.feihua.doc.core.parser;

import java.util.List;

import com.feihua.doc.bean.Api;
import com.feihua.doc.core.context.JDocContext;
import com.feihua.doc.core.exception.JDocException;
import org.apache.commons.lang3.StringUtils;

import com.feihua.doc.core.constant.Constant;
import com.sun.javadoc.ClassDoc;

public class ApiParserBuilder {

    private static AbstractApiParser apiParser;

    public static AbstractApiParser getParser() {
        if (apiParser == null) {
            String apiParserName = JDocContext.getContext().getjDocConfig().getValue("parser.name");

            String apiParserClassName = "";

            if (StringUtils.equals(Constant.SPRING_MVC, apiParserName)) {
                apiParserClassName = "com.feihua.doc.core.parser.springmvc.SpringMVCApiParser";
            }

            if (StringUtils.isBlank(apiParserClassName)) {
                throw new JDocException("not find apiParser,please check parser.name para");
            }

            try {
                apiParser = (AbstractApiParser) Class.forName(apiParserClassName).newInstance();
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | NullPointerException e) {
                throw new JDocException("getParser apiParser exception,please check parser.name para");
            }
        }
        return apiParser;
    }

    

}
