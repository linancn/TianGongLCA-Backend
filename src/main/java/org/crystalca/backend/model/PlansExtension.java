package org.crystalca.backend.model;

import org.crystalca.backend.entity.Plans;

public class PlansExtension extends Plans {
    private Long parentCount;

    public Long getParentCount() {
        return parentCount;
    }

    public void setParentCount(Long parentCount) {
        this.parentCount = parentCount;
    }
}