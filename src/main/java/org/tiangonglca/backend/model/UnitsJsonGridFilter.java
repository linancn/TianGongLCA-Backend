package org.tiangonglca.backend.model;

public class UnitsJsonGridFilter extends BaseGridFilter {
    private Long projectId;

    private String unitGroupId;

    private String name;

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getUnitGroupId() {
        return unitGroupId;
    }

    public void setUnitGroupId(String unitGroupId) {
        this.unitGroupId = unitGroupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
