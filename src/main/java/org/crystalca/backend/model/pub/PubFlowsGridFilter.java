package org.crystalca.backend.model.pub;

import org.crystalca.backend.model.BaseGridFilter;

public class PubFlowsGridFilter extends BaseGridFilter {
    private String dataName;

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }
}
