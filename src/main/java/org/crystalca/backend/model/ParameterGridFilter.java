package org.crystalca.backend.model;

public class ParameterGridFilter extends BaseGridFilter {
    private Long projectId;
    private Long processId;

    public Long getProjectId() {
        return projectId;
    }

    public Long getProcessId() {
        return processId;
    }

    public void setProcessId(Long processId) {
        this.processId = processId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
}
