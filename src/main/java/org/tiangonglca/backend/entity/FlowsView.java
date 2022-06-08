package org.tiangonglca.backend.entity;

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
 * @author TianGongLCA
 * @since 2022-02-13
 */
@TableName("flows_view")
public class FlowsView implements Serializable {

    private static final long serialVersionUID = 1L;

    private String dataName;

    private String version;

    private String description;

    private String synonyms;

    private String formula;

    private String id;

    private Timestamp lastChange;

    private String flowType;

    private String cas;

    private String categoryId;

    private String categoryPath;

    private String categoryName;

    private Long flowPropertyCount;

    private String locationId;

    private String locationName;

    @TableId(value = "pkid", type = IdType.INPUT)
    private Long pkid;

    private Long projectId;

    private String database;

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
    public String getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(String synonyms) {
        this.synonyms = synonyms;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
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
    public String getFlowType() {
        return flowType;
    }

    public void setFlowType(String flowType) {
        this.flowType = flowType;
    }
    public String getCas() {
        return cas;
    }

    public void setCas(String cas) {
        this.cas = cas;
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

    public Long getFlowPropertyCount() {
        return flowPropertyCount;
    }

    public void setFlowPropertyCount(Long flowPropertyCount) {
        this.flowPropertyCount = flowPropertyCount;
    }
    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
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
    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }
    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }
}
