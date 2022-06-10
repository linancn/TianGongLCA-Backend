package earth.tiangong.lca.backend.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * <p>
 * 
 * </p>
 *
 * @author TianGongLCA
 * @since 2022-01-20
 */
@KeySequence(value = "parameter_pkid_seq")
public class Parameter implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private String formula;

    private Double value;

    private Double min;

    private Double max;

    private String sd;

    private Long projectId;

    private String id;

    private String processId;
    
    @TableId(value = "pkid", type = IdType.INPUT)
    private Long pkid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public String getSd() {
        return sd;
    }

    public void setSd(String sd) {
        this.sd = sd;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public Long getPkid() {
        return pkid;
    }

    public void setPkid(Long pkid) {
        this.pkid = pkid;
    }

    @Override
    public String toString() {
        return "Parameter{" + "name=" + name + ", formula=" + formula + ", value=" + value + ", min=" + min + ", max=" + max + ", sd=" + sd + ", projectId=" + projectId + ", id=" + id + ", processId=" + processId + ", pkid=" + pkid + "}";
    }
}
