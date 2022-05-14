package org.crystalca.backend.model;

public class CopyFromOtherProjectsFilter {
    private Long projectId;

    private Long othProjectId;

    private String othId;

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getOthProjectId() {
        return othProjectId;
    }

    public void setOthProjectId(Long othProjectId) {
        this.othProjectId = othProjectId;
    }

    public String getOthId() {
        return othId;
    }

    public void setOthId(String othId) {
        this.othId = othId;
    }
}