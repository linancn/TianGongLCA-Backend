package org.tiangonglca.backend.model.pub;

import org.tiangonglca.backend.model.BaseGridFilter;

public class PubFlowPropertiesGridFilter extends BaseGridFilter {
    private String dataName;

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }
}
