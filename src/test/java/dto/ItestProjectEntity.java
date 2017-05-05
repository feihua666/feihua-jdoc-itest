package dto;


public class ItestProjectEntity extends DataEntity {
    private String name;

    private Integer ownerOfficeId;

    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getOwnerOfficeId() {
        return ownerOfficeId;
    }

    public void setOwnerOfficeId(Integer ownerOfficeId) {
        this.ownerOfficeId = ownerOfficeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}