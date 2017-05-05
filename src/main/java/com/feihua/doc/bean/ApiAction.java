package com.feihua.doc.bean;

import java.util.List;

/**
 * 接口描述
 * 
 * @author lianghao
 *
 *         2017年3月23日
 */
public class ApiAction {

    /**
     * 接口标题
     */
    private String title;

    /**
     * 接口描述
     */
    private String desc;

    /**
     * 请求URL
     */
    private String url;
    
    /**
     * 请求类型 post,get
     */
    private String requestType;

    /**
     * 请求参数
     */
    private List<ApiRequestParam> reqParams;

    /**
     * 返回参数
     */
    private List<ApiResponseParam> respParams;

    /**
     * 请求返回样本
     */
    private String respText;
    private String respDataType;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<ApiRequestParam> getReqParams() {
        return reqParams;
    }

    public void setReqParams(List<ApiRequestParam> reqParams) {
        this.reqParams = reqParams;
    }

    public List<ApiResponseParam> getRespParams() {
        return respParams;
    }

    public void setRespParams(List<ApiResponseParam> respParams) {
        this.respParams = respParams;
    }

    public String getRespText() {
        return respText;
    }

    public void setRespText(String respText) {
        this.respText = respText;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    
    public String getRequestType() {
        return requestType;
    }

    
    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getRespDataType() {
        return respDataType;
    }

    public void setRespDataType(String respDataType) {
        this.respDataType = respDataType;
    }
}
