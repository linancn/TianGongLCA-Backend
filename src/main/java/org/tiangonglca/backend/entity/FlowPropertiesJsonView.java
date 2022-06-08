package org.tiangonglca.backend.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 
 * </p>
 *
 * @author TianGongLCA
 * @since 2022-02-13
 */
@TableName("flow_properties_json_view")
public class FlowPropertiesJsonView implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long projectId;

    private Long flowPkid;

    private String flowId;

    private String flowPropertyId;

    private Double conversionFactor;

    private Boolean referenceFlowProperty;

    private String dataName;

    private String version;

    private String description;

    private Timestamp lastChange;

    private String flowPropertyType;

    private String categoryId;

    // private String categoryPath;

    // private String categoryName;

    // private String database;

    private String release;

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFlowPropertyId() {
        return flowPropertyId;
    }

    public void setFlowPropertyId(String flowPropertyId) {
        this.flowPropertyId = flowPropertyId;
    }

    public Timestamp getLastChange() {
        return lastChange;
    }

    public void setLastChange(Timestamp lastChange) {
        this.lastChange = lastChange;
    }

    public String getFlowPropertyType() {
        return flowPropertyType;
    }

    public void setFlowPropertyType(String flowPropertyType) {
        this.flowPropertyType = flowPropertyType;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    // public String getCategoryPath() {
    //     return categoryPath;
    // }

    // public void setCategoryPath(String categoryPath) {
    //     this.categoryPath = categoryPath;
    // }

    // public String getCategoryName() {
    //     return categoryName;
    // }

    // public void setCategoryName(String categoryName) {
    //     this.categoryName = categoryName;
    // }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
    // public String getDatabase() {
    //     return database;
    // }

    // public void setDatabase(String database) {
    //     this.database = database;
    // }
    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
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
}
