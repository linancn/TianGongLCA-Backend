package org.crystalca.backend.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author CrystaLCA
 * @since 2022-01-20
 */
@TableName("model_nodes_json")
public class ModelNodesJson implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long projectId;

    private Long planPkid;

    private String planId;

    private String nodeId;

    private String nodeType;

    private String nodeName;

    private String nodeDescription;

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

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getNodeDescription() {
        return nodeDescription;
    }

    public void setNodeDescription(String nodeDescription) {
        this.nodeDescription = nodeDescription;
    }
}
