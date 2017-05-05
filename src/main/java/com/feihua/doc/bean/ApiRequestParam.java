package com.feihua.doc.bean;

/**
 * 请求参数描述
 * 
 * @author lianghao
 *
 *  2017年3月23日
 */
/**
 * 
 * @author lianghao
 *
 *         2017年3月23日
 */
public class ApiRequestParam {

    /**
     * 参数名称
     */
    private String name;

    /**
     * 是否必传
     */

    private String required;

    /**
     * 参数描述
     */
    private String desc;

    /**
     * 请求类型
     */
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
