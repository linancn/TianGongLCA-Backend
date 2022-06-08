package org.tiangonglca.backend.model;

public class ModelFlowsJsonGridFilter extends BaseGridFilter {
    private Long projectId;

    private String planId;

    private String edgeTargetId;

    private String edgeSourceId;

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getEdgeSourceId() {
        return edgeSourceId;
    }

    public void setEdgeSourceId(String edgeSourceId) {
        this.edgeSourceId = edgeSourceId;
    }

    public String getEdgeTargetId() {
        return edgeTargetId;
    }

    public void setEdgeTargetId(String edgeTargetId) {
        this.edgeTargetId = edgeTargetId;
    }
}
