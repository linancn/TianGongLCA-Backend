package org.tiangonglca.backend.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 
 * </p>
 *
 * @author TianGongLCA
 * @since 2022-01-20
 */
@TableName("parameters_json")
public class ParametersJson implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long projectId;

    private String id;

    private Long processPkid;

    private String processId;

    private String name;

    private String formula;

    private Double value;

    private String version;

    private String context;

    private String description;

    private Boolean inputParameter;

    private String parameterScope;

    private Double uncertaintyGeomMean;

    private Double uncertaintyGeomSd;

    private String uncertaintyDistributionType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getInputParameter() {
        return inputParameter;
    }

    public void setInputParameter(Boolean inputParameter) {
        this.inputParameter = inputParameter;
    }

    public String getParameterScope() {
        return parameterScope;
    }

    public void setParameterScope(String parameterScope) {
        this.parameterScope = parameterScope;
    }

    public String getUncertaintyDistributionType() {
        return uncertaintyDistributionType;
    }

    public void setUncertaintyDistributionType(String uncertaintyDistributionType) {
        this.uncertaintyDistributionType = uncertaintyDistributionType;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Double getUncertaintyGeomMean() {
        return uncertaintyGeomMean;
    }

    public void setUncertaintyGeomMean(Double uncertaintyGeomMean) {
        this.uncertaintyGeomMean = uncertaintyGeomMean;
    }

    public Double getUncertaintyGeomSd() {
        return uncertaintyGeomSd;
    }

    public void setUncertaintyGeomSd(Double uncertaintyGeomSd) {
        this.uncertaintyGeomSd = uncertaintyGeomSd;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getProcessPkid() {
        return processPkid;
    }

    public void setProcessPkid(Long processPkid) {
        this.processPkid = processPkid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String toJsonString() {
        if (this.id == null)
            return "";
        return "{\"@id\":\"" + this.id + "\",\"name\":\"" + this.name + "\",\"@type\":\"" + "Parameter" + "\",\"value\":" + this.value + ",\"formula\":\"" + this.formula + "\",\"version\":\"" + this.version + "\",\"@context\":\"" + this.context
                + "\",\"description\":\"" + this.description + "\",\"uncertainty\":{" + "\"@type\":\"" + "Uncertainty" + "\",\"geomSd\":" + this.uncertaintyGeomSd + ",\"geomMean\":" + this.uncertaintyGeomMean + ",\"distributionType\":\""
                + this.uncertaintyDistributionType + "\"},\"inputParameter\":" + (this.inputParameter == null || !this.inputParameter ? "false" : "true") + ",\"parameterScope\":\"" + this.parameterScope + "\"},";
    }
}
