package org.crystalca.backend.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 
 * </p>
 *
 * @author CrystaLCA
 * @since 2022-02-13
 */
@TableName("flow_properties_json")
public class FlowPropertiesJson implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long projectId;

    private Long flowPkid;

    private String flowId;

    private String flowPropertyId;

    private Double conversionFactor;

    private Boolean referenceFlowProperty;

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getFlowPkid() {
        return flowPkid;
    }

    public void setFlowPkid(Long flowPkid) {
        this.flowPkid = flowPkid;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public String getFlowPropertyId() {
        return flowPropertyId;
    }

    public void setFlowPropertyId(String flowPropertyId) {
        this.flowPropertyId = flowPropertyId;
    }

    public Double getConversionFactor() {
        return conversionFactor;
    }

    public void setConversionFactor(Double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public Boolean getReferenceFlowProperty() {
        return referenceFlowProperty;
    }

    public void setReferenceFlowProperty(Boolean referenceFlowProperty) {
        this.referenceFlowProperty = referenceFlowProperty;
    }

    public String toJsonString() {
        if (this.flowPropertyId == null)
            return "";
        return "{\"flowProperty\":{\"@id\":\"" + this.flowPropertyId + "\"},\"conversionFactor\":" + this.conversionFactor.toString() + ",\"referenceFlowProperty\":" + (this.referenceFlowProperty == null || !this.referenceFlowProperty ? "false" : "true")
                + "},";
    }

    public String toJsonString(boolean referenceFlowProperty, double refConversionFactor) {
        this.referenceFlowProperty = referenceFlowProperty;
        this.conversionFactor = this.conversionFactor / refConversionFactor;
        return toJsonString();
    }
}
