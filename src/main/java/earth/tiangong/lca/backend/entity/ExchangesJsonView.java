package earth.tiangong.lca.backend.entity;

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
@TableName("exchanges_json_view")
public class ExchangesJsonView implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long projectId;

    private Long processPkid;

    private String processId;

    private Boolean input;

    private Double amount;

    private String amountFormula;

    private Long internalId;

    private String description;

    private Boolean avoidedProduct;

    private Boolean quantitativeReference;

    private String flowPkid;

    private String flowId;

    private String flowName;

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

    public Long getInternalId() {
        return internalId;
    }

    public void setInternalId(Long internalId) {
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

    public String getFlowPkid() {
        return flowPkid;
    }

    public void setFlowPkid(String flowPkid) {
        this.flowPkid = flowPkid;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public String getFlowName() {
        return flowName;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }
}
