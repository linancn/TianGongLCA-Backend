package org.tiangonglca.backend.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author TianGongLCA
 * @since 2022-01-20
 */
@TableName("model_edges_json")
public class ModelEdgesJson implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long projectId;

    private Long planPkid;

    private String planId;

    private String planSourceId;

    private String planTargetId;

    private String processSourceId;

    private String processTargetId;

    private String edgeSourceId;

    private String edgeTargetId;

    private String flows;

    public Long getPlanPkid() {
        return planPkid;
    }

    public void setPlanPkid(Long planPkid) {
        this.planPkid = planPkid;
    }

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

    public String getFlows() {
        return flows;
    }

    public void setFlows(String flows) {
        this.flows = flows;
    }
}
