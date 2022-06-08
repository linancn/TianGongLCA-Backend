package org.tiangonglca.backend.model;

public class FlowsViewGridFilter extends BaseGridFilter {
    private Long projectId;

    private String dataName;

    private String flowType;

    private Boolean otherProject;

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public String getFlowType() {
        return flowType;
    }

    public void setFlowType(String flowType) {
        this.flowType = flowType;
    }

    public Boolean getOtherProject() {
        return otherProject;
    }

    public void setOtherProject(Boolean otherProject) {
        this.otherProject = otherProject;
    }
}
