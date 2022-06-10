package earth.tiangong.lca.backend.controller.pub;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import earth.tiangong.lca.backend.entity.pub.PubFlowProperties;
import earth.tiangong.lca.backend.model.GridData;
import earth.tiangong.lca.backend.model.pub.PubFlowPropertiesGridFilter;
import earth.tiangong.lca.backend.model.pub.CopyFromPublicDatabaseFilter;
import earth.tiangong.lca.backend.service.pub.IPubFlowPropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import static org.springframework.http.ResponseEntity.ok;

@Controller
@RequestMapping("/pub/flowproperty")
public class PubFlowPropertiesController {

    @Autowired
    private IPubFlowPropertiesService iPubFlowPropertiesService;

    @GetMapping("/getgrid")
    public ResponseEntity<GridData<PubFlowProperties>> getGrid(PubFlowPropertiesGridFilter filter) throws Exception {
        filter = filter == null ? new PubFlowPropertiesGridFilter() : filter;
        return ok(iPubFlowPropertiesService.getGrid(filter));
    }

    @PutMapping("/copy")
    public ResponseEntity<String> copy(@RequestBody CopyFromPublicDatabaseFilter filter) {
        return ok(iPubFlowPropertiesService.copy(filter));
    }
}
