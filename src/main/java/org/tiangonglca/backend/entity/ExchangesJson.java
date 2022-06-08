package org.tiangonglca.backend.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author TianGongLCA
 * @since 2022-01-21
 */
@TableName("exchanges_json")
public class ExchangesJson implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long projectId;

    private Long processPkid;

    private String processId;

    private Boolean input;

    private Double amount;

    private String amountFormula;

    private Integer internalId;

    private String description;

    private Boolean avoidedProduct;

    private Boolean quantitativeReference;

    private String flowId;

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

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public Boolean getInput() {
        return input;
    }

    public void setInput(Boolean input) {
        this.input = input;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getAmountFormula() {
        return amountFormula;
    }

    public void setAmountFormula(String amountFormula) {
        this.amountFormula = amountFormula;
    }

    public Integer getInternalId() {
        return internalId;
    }

    public void setInternalId(Integer internalId) {
        this.internalId = internalId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAvoidedProduct() {
        return avoidedProduct;
    }

    public void setAvoidedProduct(Boolean avoidedProduct) {
        this.avoidedProduct = avoidedProduct;
    }

    public Boolean getQuantitativeReference() {
        return quantitativeReference;
    }

    public void setQuantitativeReference(Boolean quantitativeReference) {
        this.quantitativeReference = quantitativeReference;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public String toJsonString() {
        if (this.flowId == null)
            return "";
        return "{\"flow\":{" + "\"@id\":\"" + this.flowId + "\"},\"input\":" + (this.input == null || !this.input ? "false" : "true") + ",\"amount\":" + this.amount + ",\"amountFormula\":\"" + this.amountFormula + "\",\"internalId\":" + this.internalId
                + ",\"description\":\"" + this.description + "\",\"avoidedProduct\":" + (this.avoidedProduct == null || !this.avoidedProduct ? "false" : "true") + ",\"quantitativeReference\":"
                + (this.quantitativeReference == null || !this.quantitativeReference ? "false" : "true") + "},";
    }
}
