package org.crystalca.backend.model;

public class ParametersJsonGridFilter extends BaseGridFilter {
    private Long projectId;

    private String processId;

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }
}
