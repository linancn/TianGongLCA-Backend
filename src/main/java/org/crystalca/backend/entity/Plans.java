package org.crystalca.backend.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 
 * </p>
 *
 * @author CrystaLCA
 * @since 2022-01-17
 */
@TableName("plans")
@KeySequence(value = "plans_pkid_seq")
public class Plans implements Serializable {

    private static final long serialVersionUID = 1L;

    private String dataName;

    private String description;

    private String planType;

    private String locationId;

    private Timestamp lastChange;

    private Long projectId;

    private String categoryId;

    private String fixedProcessId;

    private String modelCells;

    private String modelFlows;

    private String version;

    private Double scalingFactor;

    private String id;

    @TableId(value = "pkid", type = IdType.INPUT)
    private Long pkid;

    // private Long parentCount;

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public Timestamp getLastChange() {
        return lastChange;
    }

    public void setLastChange(Timestamp lastChange) {
        this.lastChange = lastChange;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getFixedProcessId() {
        return fixedProcessId;
    }

    public void setFixedProcessId(String fixedProcessId) {
        this.fixedProcessId = fixedProcessId;
    }

    public String getModelCells() {
        return modelCells;
    }

    public void setModelCells(String modelCells) {
        this.modelCells = modelCells;
    }

    public String getModelFlows() {
        return modelFlows;
    }

    public void setModelFlows(String modelFlows) {
        this.modelFlows = modelFlows;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Double getScalingFactor() {
        return scalingFactor;
    }

    public void setScalingFactor(Double scalingFactor) {
        this.scalingFactor = scalingFactor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getPkid() {
        return pkid;
    }

    public void setPkid(Long pkid) {
        this.pkid = pkid;
    }
}
