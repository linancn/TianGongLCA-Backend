package org.tiangonglca.backend.model;

import org.tiangonglca.backend.entity.ModelFlowsJson;

public class ModelFlowsJsonExtension extends ModelFlowsJson {

    private String planSourceName;

    private String planTargetName;

    private String processSourceName;

    private String processTargetName;

    private Boolean available;

    public ModelFlowsJsonExtension(ModelFlowsJson data) {
        this.setProjectId(data.getProjectId());
        this.setPlanId(data.getPlanId());
        this.setPlanSourceId(data.getPlanSourceId());
        this.setPlanTargetId(data.getPlanTargetId());
        this.setProcessSourceId(data.getProcessSourceId());
        this.setProcessTargetId(data.getProcessTargetId());
        this.setEdgeSourceId(data.getEdgeSourceId());
        this.setEdgeTargetId(data.getEdgeTargetId());
        this.setPlanSourceName("");
        this.setPlanTargetName("");
        this.setProcessSourceName("");
        this.setProcessTargetName("");
        this.setAvailable(true);
    }

    public String getPlanSourceName() {
        return planSourceName;
    }

    public void setPlanSourceName(String planSourceName) {
        this.planSourceName = planSourceName;
    }

    public String getProcessSourceName() {
        return processSourceName;
    }

    public void setProcessSourceName(String processSourceName) {
        this.processSourceName = processSourceName;
    }

    public String getPlanTargetName() {
        return planTargetName;
    }

    public void setPlanTargetName(String planTargetName) {
        this.planTargetName = planTargetName;
    }

    public String getProcessTargetName() {
        return processTargetName;
    }

    public void setProcessTargetName(String processTargetName) {
        this.processTargetName = processTargetName;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
