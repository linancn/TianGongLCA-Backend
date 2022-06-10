package earth.tiangong.lca.backend.entity;

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
@TableName("model_flows_json")
public class ModelFlowsJson implements Serializable {

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

    public String toJsonString() {
        if (this.flows == null || this.flows.trim().equals(""))
            return "";
        return "{\"edgeSourceId\":\"" + this.edgeSourceId + "\",\"edgeTargetId\":\"" + this.edgeTargetId + "\",\"planSourceId\":\"" + this.planSourceId + "\",\"planTargetId\":\"" + this.planTargetId + "\",\"processSourceId\":\"" + this.processSourceId
                + "\",\"processTargetId\":\"" + this.processTargetId + "\",\"flows\":" + this.flows + "},";
    }

    public String toJsonString(String edgeSourceId, String edgeTargetId, String planSourceId, String planTargetId, String processSourceId, String processTargetId, String flows) {
        this.edgeSourceId = edgeSourceId;
        this.edgeTargetId = edgeTargetId;
        this.planSourceId = planSourceId;
        this.planTargetId = planTargetId;
        this.processSourceId = processSourceId;
        this.processTargetId = processTargetId;
        this.flows = flows;
        return toJsonString();
    }
}
