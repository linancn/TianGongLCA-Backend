package org.crystalca.backend.model;

public class ModelFlowsJsonViewGridFilter extends BaseGridFilter {
    private Long projectId;

    private String planId;

    private String edgeTargetId;

    private String edgeSourceId;

    private String planSourceId;

    private String planTargetId;

    private String processTargetId;
    
    private String processSourceId;

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

    public String getPlanSourceId() {
        return planSourceId;
    }

    public void setPlanSourceId(String planSourceId) {
        this.planSourceId = planSourceId;
    }

    public String getProcessSourceId() {
        return processSourceId;
    }

    public void setProcessSourceId(String processSourceId) {
        this.processSourceId = processSourceId;
    }

    public String getPlanTargetId() {
        return planTargetId;
    }

    public void setPlanTargetId(String planTargetId) {
        this.planTargetId = planTargetId;
    }

    public String getProcessTargetId() {
        return processTargetId;
    }

    public void setProcessTargetId(String processTargetId) {
        this.processTargetId = processTargetId;
    }
}
