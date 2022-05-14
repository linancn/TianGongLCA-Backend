package org.crystalca.backend.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author CrystaLCA
 * @since 2022-02-17
 */

@TableName("units_json")
public class UnitsJson implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private String version;

    private String type;

    private String synonyms;

    private String description;

    private String unitGroupId;

    private String id;

    private Double conversionFactor;

    private Boolean referenceUnit;

    private Long unitGroupPkid;

    private Long projectId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getType() {
        return type;
    }

    public void setTags(String type) {
        this.type = type;
    }

    public String getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(String synonyms) {
        this.synonyms = synonyms;
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

    public String getUnitGroupId() {
        return unitGroupId;
    }

    public void setUnitGroupId(String unitGroupId) {
        this.unitGroupId = unitGroupId;
    }

    public Double getConversionFactor() {
        return conversionFactor;
    }

    public void setConversionFactor(Double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public Long getUnitGroupPkid() {
        return unitGroupPkid;
    }

    public void setUnitGroupPkid(Long unitGroupPkid) {
        this.unitGroupPkid = unitGroupPkid;
    }

    public Boolean getReferenceUnit() {
        return referenceUnit;
    }

    public void setReferenceUnit(Boolean referenceUnit) {
        this.referenceUnit = referenceUnit;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String toJsonString() {
        if (this.id == null)
            return "";
        return "{\"@id\":\"" + this.id + "\",\"name\":\"" + this.name + "\",\"@type\":\"" + this.type + "\",\"version\":\"" + this.version + "\",\"synonyms\":" + this.synonyms + ",\"description\":\"" + this.description + "\",\"conversionFactor\":"
                + this.conversionFactor.toString() + ",\"referenceUnit\":" + (this.referenceUnit == null || !this.referenceUnit ? "false" : "true") + "},";
    }

    public String toJsonString(boolean referenceUnit, double refConversionFactor) {
        this.referenceUnit = referenceUnit;
        this.conversionFactor = this.conversionFactor / refConversionFactor;
        return toJsonString();
    }
}
