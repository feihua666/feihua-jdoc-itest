package dto;


public class ItestMockDataEntity extends DataEntity {
    private Integer projectId;

    private Integer versionId;

    private Integer actionId;

    private String mockDataRule;

    private String mockData;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getVersionId() {
        return versionId;
    }

    public void setVersionId(Integer versionId) {
        this.versionId = versionId;
    }

    public Integer getActionId() {
        return actionId;
    }

    public void setActionId(Integer actionId) {
        this.actionId = actionId;
    }

    public String getMockDataRule() {
        return mockDataRule;
    }

    public void setMockDataRule(String mockDataRule) {
        this.mockDataRule = mockDataRule == null ? null : mockDataRule.trim();
    }

    public String getMockData() {
        return mockData;
    }

    public void setMockData(String mockData) {
        this.mockData = mockData == null ? null : mockData.trim();
    }
}