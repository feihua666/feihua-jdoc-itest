package com.feihua.doc.core.template;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreemarketTemplate {

    static Configuration cfg = new Configuration();

    static {
        cfg.setDefaultEncoding("UTF-8");
        cfg.setClassForTemplateLoading(FreemarketTemplate.class, "ftl");
    }
    
    public static void output(String templatePath,String outputPath,Map<Object,Object> paras){
        try {
            
            Template template = cfg.getTemplate(templatePath);
            Writer out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(outputPath), "UTF-8"));
            template.process(paras, out);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

}
