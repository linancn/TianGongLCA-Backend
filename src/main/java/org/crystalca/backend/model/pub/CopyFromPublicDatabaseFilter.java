package org.crystalca.backend.model.pub;

public class CopyFromPublicDatabaseFilter {
    private Long projectId;

    private String pubId;

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getPubId() {
        return pubId;
    }

    public void setPubId(String pubId) {
        this.pubId = pubId;
    }
}
