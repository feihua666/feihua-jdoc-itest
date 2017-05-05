package dto;


public class ItestParameterEntity extends DataEntity {
    /**
     * 参数名称
     */
    private String name;

    private String identifier;

    private String isRequred;

    private String description;

    private String dataType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier == null ? null : identifier.trim();
    }

    public String getIsRequred() {
        return isRequred;
    }

    public void setIsRequred(String isRequred) {
        this.isRequred = isRequred == null ? null : isRequred.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType == null ? null : dataType.trim();
    }
}