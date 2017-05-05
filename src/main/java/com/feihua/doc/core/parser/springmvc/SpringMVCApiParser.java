package com.feihua.doc.core.parser.springmvc;

import java.awt.*;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.feihua.doc.bean.Api;
import com.feihua.doc.bean.ApiRequestParam;
import com.feihua.doc.core.constant.Constant;
import com.feihua.doc.core.exception.JDocException;
import com.feihua.doc.utils.ClassUtils;
import com.sun.javadoc.Parameter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.feihua.doc.bean.ApiAction;
import com.feihua.doc.bean.ApiResponseParam;
import com.feihua.doc.core.parser.AbstractApiParser;
import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.Tag;

public class SpringMVCApiParser extends AbstractApiParser {

    @Override
    public List<String> filterController(List<String> fileNames) {

        List<String> controllerFileNames = new ArrayList<String>();

        for (String fileName : fileNames) {
            String packageFileName = fileName.substring(Constant.JAVA_FILE_PATH.length(), fileName.length() - Constant.JAVA_FILE_SUFFIX.length()).replace(File.separator, ".");
            try {
                Class<?> fileClass = Class.forName(packageFileName);
                Controller controller = fileClass.getAnnotation(Controller.class);
                RestController restController = fileClass.getAnnotation(RestController.class);
                if (controller != null || restController != null) {
                    controllerFileNames.add(fileName);
                }
            } catch (ClassNotFoundException e) {

            }
        }
        return controllerFileNames;
    }

    @Override
    public List<Api> generateApi(ClassDoc[] classDocs) {

        List<Api> apis = new ArrayList<Api>();
        for (ClassDoc classDoc : classDocs) {

            Api api = new Api();
            api.setTitle(classDoc.commentText());

            MethodDoc[] methodDocs = classDoc.methods(false);

            Class<?> controllerClass = null;
            try {
                controllerClass = Class.forName(classDoc.qualifiedTypeName());
            } catch (ClassNotFoundException e) {

            }

            RequestMapping controllerRequestMapping = controllerClass.getAnnotation(RequestMapping.class);
            Controller controller = controllerClass.getAnnotation(Controller.class);

            if(controller == null){
                continue;
            }

            String url = "";
            if (controllerRequestMapping != null) {
                url = controllerRequestMapping.value()[0];
            }

            List<ApiAction> apiActions = new ArrayList<ApiAction>(methodDocs.length);

            for (MethodDoc methodDoc : methodDocs) {

                ApiAction apiAction = new ApiAction();

                Class<?>[] paramTypes = null;
                Method method = null;
                try {
                    paramTypes = ClassUtils.getParamTypes(methodDoc);
                    method = controllerClass.getDeclaredMethod(methodDoc.name(), paramTypes);
                } catch (Exception e) {

                }
                Tag[] tags = methodDoc.tags();

                List<ApiRequestParam> apiRequestParams = generateApiReqeustParam(tags);
                if(!CollectionUtils.isEmpty(apiRequestParams)){
                    for(ApiRequestParam apiRequestParam:apiRequestParams){
                        if(StringUtils.isEmpty(apiRequestParam.getType())){
                            Parameter[] parameters = methodDoc.parameters();
                            for(Parameter parameter : parameters){
                                if(apiRequestParam.getName().equals(parameter.name())){
                                    apiRequestParam.setType(parameter.type().simpleTypeName());
                                    break;
                                }
                            }

                        }
                    }
                }
                List<ApiResponseParam> apiResponseParams = generateApiResponseParam(tags);
                String title = getTitle(tags);
                String respBody = getRespBody(tags);
                String respDataType = getRespDataType(tags);
                String requestUrl = generateRequestUrl(method, url);
                String requestType = getRequestType(method);

                apiAction.setRequestType(requestType);
                apiAction.setTitle(title);
                apiAction.setRespText(respBody);
                apiAction.setRespParams(apiResponseParams);
                apiAction.setReqParams(apiRequestParams);
                apiAction.setUrl(requestUrl);
                apiAction.setDesc(methodDoc.commentText());
                apiAction.setRespDataType(respDataType);
                apiActions.add(apiAction);
            }

            api.setApiActions(apiActions);
            apis.add(api);
        }
        return apis;

    }


    private String generateRequestUrl(Method method, String url) {
        RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
        if (requestMapping == null) {
            throw new JDocException("please add requestMapping annotion on this method " + method.getName());
        }
        return url + requestMapping.value()[0];
    }

    private String getRequestType(Method method) {
        StringBuilder requestType = new StringBuilder();
        RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
        RequestMethod[] requestMethods = requestMapping.method();
        if (requestMethods != null) {
            for (int i = 0; i < requestMethods.length; i++) {
                if (RequestMethod.GET == requestMethods[i]) {
                    requestType.append(Constant.GET).append(",");
                }

                if (RequestMethod.POST == requestMethods[i]) {
                    requestType.append(Constant.POST).append(",");
                }
            }
        }
        if (requestType.length() > 0) {
            return requestType.substring(0, requestType.length() - 1);
        }
        
        return Constant.POST+","+Constant.GET;
    }
}
