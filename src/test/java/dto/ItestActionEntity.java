package dto;


public class ItestActionEntity extends DataEntity {
    /**
     * 名称
     */
    private String name;

    /**
     * 请求类型
     *
     *
     */
    private String requestType;
    /**
     * 描述
     *
     * @title 测试接口4
     * @respParam username|用户名|Integer|必填
     * @respBody dto.ItestActionDto
     */
    private String description;

    private String requestUrl;
    /**
     * 名称
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType == null ? null : requestType.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl == null ? null : requestUrl.trim();
    }
}