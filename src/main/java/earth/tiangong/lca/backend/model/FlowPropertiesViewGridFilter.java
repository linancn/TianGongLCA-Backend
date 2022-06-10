package earth.tiangong.lca.backend.model;

public class FlowPropertiesViewGridFilter extends BaseGridFilter {
    private Long projectId;

    private Boolean otherProject;

    private String dataName;

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Boolean getOtherProject() {
        return otherProject;
    }

    public void setOtherProject(Boolean otherProject) {
        this.otherProject = otherProject;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }
}
