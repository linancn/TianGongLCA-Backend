package earth.tiangong.lca.backend.model;

public class ProcessesGridFilter extends BaseGridFilter {
    private Long projectId;

    private String dataName;

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

    public Boolean getOtherProject() {
        return otherProject;
    }

    public void setOtherProject(Boolean otherProject) {
        this.otherProject = otherProject;
    }
}
