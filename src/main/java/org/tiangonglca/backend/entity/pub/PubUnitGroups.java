package org.tiangonglca.backend.entity.pub;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * <p>
 * 
 * </p>
 *
 * @author TianGongLCA
 * @since 2022-02-17
 */
@TableName("uslci__unit_groups")
public class PubUnitGroups implements Serializable {

    private static final long serialVersionUID = 1L;

    private String dataName;

    private String version;

    private String tags;

    private String library;

    private String description;

    private String id;

    private Timestamp lastChange;

    private String categoryId;

    private String categoryName;

    private String categoryPath;

    private String categoryType;

    private String units;

    private String defaultFlowPropertyId;

    private String defaultFlowPropertyName;

    private String defaultFlowPropertyCategoryPath;

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

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getLibrary() {
        return library;
    }

    public void setLibrary(String library) {
        this.library = library;
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

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryPath() {
        return categoryPath;
    }

    public void setCategoryPath(String categoryPath) {
        this.categoryPath = categoryPath;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getDefaultFlowPropertyId() {
        return defaultFlowPropertyId;
    }

    public void setDefaultFlowPropertyId(String defaultFlowPropertyId) {
        this.defaultFlowPropertyId = defaultFlowPropertyId;
    }

    public String getDefaultFlowPropertyName() {
        return defaultFlowPropertyName;
    }

    public void setDefaultFlowPropertyName(String defaultFlowPropertyName) {
        this.defaultFlowPropertyName = defaultFlowPropertyName;
    }

    public String getDefaultFlowPropertyCategoryPath() {
        return defaultFlowPropertyCategoryPath;
    }

    public void setDefaultFlowPropertyCategoryPath(String defaultFlowPropertyCategoryPath) {
        this.defaultFlowPropertyCategoryPath = defaultFlowPropertyCategoryPath;
    }
}
