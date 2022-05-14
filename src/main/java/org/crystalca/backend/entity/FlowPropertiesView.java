package org.crystalca.backend.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 
 * </p>
 *
 * @author CrystaLCA
 * @since 2022-02-13
 */
@TableName("flow_properties_view")
public class FlowPropertiesView implements Serializable {

    private static final long serialVersionUID = 1L;

    private String dataName;

    private String version;

    private String description;

    private String id;

    private Timestamp lastChange;

    private String flowPropertyType;

    private Long unitGroupPkid;

    private String unitGroupId;

    private String unitGroupName;

    private String referenceUnit;

    private String categoryId;

    private String categoryPath;

    private String categoryName;

    @TableId(value = "pkid", type = IdType.INPUT)
    private Long pkid;

    private Long projectId;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Long getUnitGroupPkid() {
        return unitGroupPkid;
    }

    public void setUnitGroupPkid(Long unitGroupPkid) {
        this.unitGroupPkid = unitGroupPkid;
    }

    public String getUnitGroupId() {
        return unitGroupId;
    }

    public void setUnitGroupId(String unitGroupId) {
        this.unitGroupId = unitGroupId;
    }

    public String getUnitGroupName() {
        return unitGroupName;
    }

    public void setUnitGroupName(String unitGroupName) {
        this.unitGroupName = unitGroupName;
    }

    public String getReferenceUnit() {
        return referenceUnit;
    }

    public void setReferenceUnit(String referenceUnit) {
        this.referenceUnit = referenceUnit;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryPath() {
        return categoryPath;
    }

    public void setCategoryPath(String categoryPath) {
        this.categoryPath = categoryPath;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getPkid() {
        return pkid;
    }

    public void setPkid(Long pkid) {
        this.pkid = pkid;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }
}
