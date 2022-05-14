package org.crystalca.backend.entity.pub;

import java.io.Serializable;
import java.sql.Timestamp;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 
 * </p>
 *
 * @author CrystaLCA
 * @since 2022-03-13
 */
@TableName("uslci__processes")
public class PubProcesses implements Serializable {

    private static final long serialVersionUID = 1L;

    private String dataName;

    private String version;

    private String tags;

    private String library;

    private String description;

    private String id;

    private Timestamp lastChange;

    private String processType;

    private String defaultAllocationMethod;

    private Boolean infrastructureProcess;

    private String dqEntry;

    private String categoryId;

    private String locationId;

    private String processDocumentationTimeDescription;

    private String processDocumentationTechnologyDescription;

    private String processDocumentationCompletenessDescription;

    private String processDocumentationDataSelectionDescription;

    private String processDocumentationInventoryMethodDescription;

    private Boolean processDocumentationCopyright;

    private Timestamp processDocumentationCreationDate;

    private String processDocumentationProjectDescription;

    private String processDocumentationGeographyDescription;

    private String exchanges;

    private Integer lastInternalId;

    private String parameters;

    private String allocationFactors;

    private String processDocumentationReviewDetails;

    private String processDocumentationDataTreatmentDescription;

    private String processDocumentationSamplingDescription;

    private String processDocumentationSources;

    private Timestamp processDocumentationValidFrom;

    private Timestamp processDocumentationValidUntil;

    private String processDocumentationDataDocumentorId;

    private String processDocumentationDataGeneratorId;

    private String processDocumentationModelingConstantsDescription;

    private String processDocumentationIntendedApplication;

    private String processDocumentationRestrictionsDescription;

    private String processDocumentationDataSetOwnerId;

    private String processDocumentationDataCollectionDescription;

    private String processDocumentationReviewerId;

    private String processDocumentationPublicationId;

    private String exchangeDqSystemId;

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

    public String getProcessType() {
        return processType;
    }

    public void setProcessType(String processType) {
        this.processType = processType;
    }

    public String getDefaultAllocationMethod() {
        return defaultAllocationMethod;
    }

    public void setDefaultAllocationMethod(String defaultAllocationMethod) {
        this.defaultAllocationMethod = defaultAllocationMethod;
    }

    public Boolean getInfrastructureProcess() {
        return infrastructureProcess;
    }

    public void setInfrastructureProcess(Boolean infrastructureProcess) {
        this.infrastructureProcess = infrastructureProcess;
    }

    public String getDqEntry() {
        return dqEntry;
    }

    public void setDqEntry(String dqEntry) {
        this.dqEntry = dqEntry;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getProcessDocumentationTimeDescription() {
        return processDocumentationTimeDescription;
    }

    public void setProcessDocumentationTimeDescription(String processDocumentationTimeDescription) {
        this.processDocumentationTimeDescription = processDocumentationTimeDescription;
    }

    public String getProcessDocumentationTechnologyDescription() {
        return processDocumentationTechnologyDescription;
    }

    public void setProcessDocumentationTechnologyDescription(String processDocumentationTechnologyDescription) {
        this.processDocumentationTechnologyDescription = processDocumentationTechnologyDescription;
    }

    public String getProcessDocumentationCompletenessDescription() {
        return processDocumentationCompletenessDescription;
    }

    public void setProcessDocumentationCompletenessDescription(String processDocumentationCompletenessDescription) {
        this.processDocumentationCompletenessDescription = processDocumentationCompletenessDescription;
    }

    public String getProcessDocumentationDataSelectionDescription() {
        return processDocumentationDataSelectionDescription;
    }

    public void setProcessDocumentationDataSelectionDescription(String processDocumentationDataSelectionDescription) {
        this.processDocumentationDataSelectionDescription = processDocumentationDataSelectionDescription;
    }

    public String getProcessDocumentationInventoryMethodDescription() {
        return processDocumentationInventoryMethodDescription;
    }

    public void setProcessDocumentationInventoryMethodDescription(String processDocumentationInventoryMethodDescription) {
        this.processDocumentationInventoryMethodDescription = processDocumentationInventoryMethodDescription;
    }

    public Boolean getProcessDocumentationCopyright() {
        return processDocumentationCopyright;
    }

    public void setProcessDocumentationCopyright(Boolean processDocumentationCopyright) {
        this.processDocumentationCopyright = processDocumentationCopyright;
    }

    public Timestamp getProcessDocumentationCreationDate() {
        return processDocumentationCreationDate;
    }

    public void setProcessDocumentationCreationDate(Timestamp processDocumentationCreationDate) {
        this.processDocumentationCreationDate = processDocumentationCreationDate;
    }

    public String getProcessDocumentationProjectDescription() {
        return processDocumentationProjectDescription;
    }

    public void setProcessDocumentationProjectDescription(String processDocumentationProjectDescription) {
        this.processDocumentationProjectDescription = processDocumentationProjectDescription;
    }

    public String getProcessDocumentationGeographyDescription() {
        return processDocumentationGeographyDescription;
    }

    public void setProcessDocumentationGeographyDescription(String processDocumentationGeographyDescription) {
        this.processDocumentationGeographyDescription = processDocumentationGeographyDescription;
    }

    public String getExchanges() {
        return exchanges;
    }

    public void setExchanges(String exchanges) {
        this.exchanges = exchanges;
    }

    public Integer getLastInternalId() {
        return lastInternalId;
    }

    public void setLastInternalId(Integer lastInternalId) {
        this.lastInternalId = lastInternalId;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public String getAllocationFactors() {
        return allocationFactors;
    }

    public void setAllocationFactors(String allocationFactors) {
        this.allocationFactors = allocationFactors;
    }

    public String getProcessDocumentationReviewDetails() {
        return processDocumentationReviewDetails;
    }

    public void setProcessDocumentationReviewDetails(String processDocumentationReviewDetails) {
        this.processDocumentationReviewDetails = processDocumentationReviewDetails;
    }

    public String getProcessDocumentationDataTreatmentDescription() {
        return processDocumentationDataTreatmentDescription;
    }

    public void setProcessDocumentationDataTreatmentDescription(String processDocumentationDataTreatmentDescription) {
        this.processDocumentationDataTreatmentDescription = processDocumentationDataTreatmentDescription;
    }

    public String getProcessDocumentationSamplingDescription() {
        return processDocumentationSamplingDescription;
    }

    public void setProcessDocumentationSamplingDescription(String processDocumentationSamplingDescription) {
        this.processDocumentationSamplingDescription = processDocumentationSamplingDescription;
    }

    public String getProcessDocumentationSources() {
        return processDocumentationSources;
    }

    public void setProcessDocumentationSources(String processDocumentationSources) {
        this.processDocumentationSources = processDocumentationSources;
    }

    public Timestamp getProcessDocumentationValidFrom() {
        return processDocumentationValidFrom;
    }

    public void setProcessDocumentationValidFrom(Timestamp processDocumentationValidFrom) {
        this.processDocumentationValidFrom = processDocumentationValidFrom;
    }

    public Timestamp getProcessDocumentationValidUntil() {
        return processDocumentationValidUntil;
    }

    public void setProcessDocumentationValidUntil(Timestamp processDocumentationValidUntil) {
        this.processDocumentationValidUntil = processDocumentationValidUntil;
    }

    public String getProcessDocumentationDataDocumentorId() {
        return processDocumentationDataDocumentorId;
    }

    public void setProcessDocumentationDataDocumentorId(String processDocumentationDataDocumentorId) {
        this.processDocumentationDataDocumentorId = processDocumentationDataDocumentorId;
    }

    public String getProcessDocumentationDataGeneratorId() {
        return processDocumentationDataGeneratorId;
    }

    public void setProcessDocumentationDataGeneratorId(String processDocumentationDataGeneratorId) {
        this.processDocumentationDataGeneratorId = processDocumentationDataGeneratorId;
    }

    public String getProcessDocumentationModelingConstantsDescription() {
        return processDocumentationModelingConstantsDescription;
    }

    public void setProcessDocumentationModelingConstantsDescription(String processDocumentationModelingConstantsDescription) {
        this.processDocumentationModelingConstantsDescription = processDocumentationModelingConstantsDescription;
    }

    public String getProcessDocumentationIntendedApplication() {
        return processDocumentationIntendedApplication;
    }

    public void setProcessDocumentationIntendedApplication(String processDocumentationIntendedApplication) {
        this.processDocumentationIntendedApplication = processDocumentationIntendedApplication;
    }

    public String getProcessDocumentationRestrictionsDescription() {
        return processDocumentationRestrictionsDescription;
    }

    public void setProcessDocumentationRestrictionsDescription(String processDocumentationRestrictionsDescription) {
        this.processDocumentationRestrictionsDescription = processDocumentationRestrictionsDescription;
    }

    public String getProcessDocumentationDataSetOwnerId() {
        return processDocumentationDataSetOwnerId;
    }

    public void setProcessDocumentationDataSetOwnerId(String processDocumentationDataSetOwnerId) {
        this.processDocumentationDataSetOwnerId = processDocumentationDataSetOwnerId;
    }

    public String getProcessDocumentationDataCollectionDescription() {
        return processDocumentationDataCollectionDescription;
    }

    public void setProcessDocumentationDataCollectionDescription(String processDocumentationDataCollectionDescription) {
        this.processDocumentationDataCollectionDescription = processDocumentationDataCollectionDescription;
    }

    public String getProcessDocumentationReviewerId() {
        return processDocumentationReviewerId;
    }

    public void setProcessDocumentationReviewerId(String processDocumentationReviewerId) {
        this.processDocumentationReviewerId = processDocumentationReviewerId;
    }

    public String getProcessDocumentationPublicationId() {
        return processDocumentationPublicationId;
    }

    public void setProcessDocumentationPublicationId(String processDocumentationPublicationId) {
        this.processDocumentationPublicationId = processDocumentationPublicationId;
    }

    public String getExchangeDqSystemId() {
        return exchangeDqSystemId;
    }

    public void setExchangeDqSystemId(String exchangeDqSystemId) {
        this.exchangeDqSystemId = exchangeDqSystemId;
    }
}
