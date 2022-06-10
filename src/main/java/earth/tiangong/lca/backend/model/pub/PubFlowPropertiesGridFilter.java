package earth.tiangong.lca.backend.model.pub;

import earth.tiangong.lca.backend.model.BaseGridFilter;

public class PubFlowPropertiesGridFilter extends BaseGridFilter {
    private String dataName;

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }
}
