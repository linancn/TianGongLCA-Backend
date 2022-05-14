package org.crystalca.backend.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import org.crystalca.backend.entity.pub.PubUnitGroups;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * <p>
 * 
 * </p>
 *
 * @author CrystaLCA
 * @since 2022-02-17
 */
@TableName("unit_groups")
@KeySequence(value = "unit_groups_pkid_seq")
public class UnitGroups implements Serializable {

    private static final long serialVersionUID = 1L;

    private String dataName;

    private String version;

    private String tags;

    private String library;

    private String description;

    private String id;

    private Timestamp lastChange;

    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String categoryId;

    private String units;

    private String defaultFlowPropertyId;

    @TableId(value = "pkid", type = IdType.INPUT)
    private Long pkid;

    private Long projectId;

    private String md5;

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

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public void copyPubData(PubUnitGroups pubData) {
        this.id = pubData.getId();
        this.dataName = pubData.getDataName();
        this.version = pubData.getVersion();
        this.tags = pubData.getTags();
        this.library = pubData.getLibrary();
        this.description = pubData.getDescription();
        this.lastChange = pubData.getLastChange();
        this.categoryId = pubData.getCategoryId();
        this.units = pubData.getUnits();
        this.defaultFlowPropertyId = pubData.getDefaultFlowPropertyId();
    }
}
