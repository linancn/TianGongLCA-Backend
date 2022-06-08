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
@TableName("model_flows_json_view")
public class ModelFlowsJsonView implements Serializable {

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

    private String flowSourceId;

    private String flowTargetId;

    private Long flowSourcePkid;

    private Long flowTargetPkid;

    private String flowSourceName;

    private String flowTargetName;

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

    public String getFlowSourceId() {
        return flowSourceId;
    }

    public void setFlowSourceId(String flowSourceId) {
        this.flowSourceId = flowSourceId;
    }

    public String getFlowTargetId() {
        return flowTargetId;
    }

    public void setFlowTargetId(String flowTargetId) {
        this.flowTargetId = flowTargetId;
    }

    public Long getFlowSourcePkid() {
        return flowSourcePkid;
    }

    public void setFlowSourcePkid(Long flowSourcePkid) {
        this.flowSourcePkid = flowSourcePkid;
    }

    public Long getFlowTargetPkid() {
        return flowTargetPkid;
    }

    public void setFlowTargetPkid(Long flowTargetPkid) {
        this.flowTargetPkid = flowTargetPkid;
    }

    public String getFlowSourceName() {
        return flowSourceName;
    }

    public void setFlowSourceName(String flowSourceName) {
        this.flowSourceName = flowSourceName;
    }

    public String getFlowTargetName() {
        return flowTargetName;
    }

    public void setFlowTargetName(String flowTargetName) {
        this.flowTargetName = flowTargetName;
    }

    public String toJsonString() {
        return "{\"sourceId\":\"" + this.flowSourceId + "\",\"targetId\":\"" + this.flowTargetId + "\"},";
    }
}
