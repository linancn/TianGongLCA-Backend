package earth.tiangong.lca.backend.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import earth.tiangong.lca.backend.entity.pub.PubFlows;

/**
 * <p>
 * 
 * </p>
 *
 * @author TianGongLCA
 * @since 2022-02-13
 */
@TableName("flows")
@KeySequence(value = "flows_pkid_seq")
public class Flows implements Serializable {

    private static final long serialVersionUID = 1L;

    private String dataName;

    private String version;

    private String tags;

    private String library;

    private String description;

    private String synonyms;

    private Boolean infrastructureFlow;

    private String formula;

    private String id;

    private Timestamp lastChange;

    private String flowType;

    private String cas;

    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String categoryId;

    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String locationId;

    private String flowProperties;

    @TableId(value = "pkid", type = IdType.INPUT)
    private Long pkid;

    private Long projectId;

    private String database;

    private String release;

    private String md5;

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

    public String getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(String synonyms) {
        this.synonyms = synonyms;
    }

    public Boolean getInfrastructureFlow() {
        return infrastructureFlow;
    }

    public void setInfrastructureFlow(Boolean infrastructureFlow) {
        this.infrastructureFlow = infrastructureFlow;
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

    public String getFlowProperties() {
        return flowProperties;
    }

    public void setFlowProperties(String flowProperties) {
        this.flowProperties = flowProperties;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
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

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public void copyPubData(PubFlows pubData) {
        this.id = pubData.getId();
        this.dataName = pubData.getDataName();
        this.version = pubData.getVersion();
        this.tags = pubData.getTags();
        this.library = pubData.getLibrary();
        this.description = pubData.getDescription();
        this.lastChange = pubData.getLastChange();
        this.categoryId = pubData.getCategoryId();
        this.synonyms = pubData.getSynonyms();
        this.infrastructureFlow = pubData.getInfrastructureFlow();
        this.formula = pubData.getFormula();
        this.flowType = pubData.getFlowType();
        this.cas = pubData.getCas();
        this.locationId = pubData.getLocationId();
    }
}
