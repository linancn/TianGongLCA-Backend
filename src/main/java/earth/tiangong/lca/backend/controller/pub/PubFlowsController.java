package earth.tiangong.lca.backend.controller.pub;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import earth.tiangong.lca.backend.entity.pub.PubFlows;
import earth.tiangong.lca.backend.model.GridData;
import earth.tiangong.lca.backend.model.pub.PubFlowsGridFilter;
import earth.tiangong.lca.backend.model.pub.CopyFromPublicDatabaseFilter;
import earth.tiangong.lca.backend.service.pub.IPubFlowsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import static org.springframework.http.ResponseEntity.ok;

@Controller
@RequestMapping("/pub/flow")
public class PubFlowsController {

    @Autowired
    private IPubFlowsService iPubFlowsService;

    @GetMapping("/getgrid")
    public ResponseEntity<GridData<PubFlows>> getGrid(PubFlowsGridFilter filter) throws Exception {
        filter = filter == null ? new PubFlowsGridFilter() : filter;
        return ok(iPubFlowsService.getGrid(filter));
    }

    @PutMapping("/copy")
    public ResponseEntity<String> copy(@RequestBody CopyFromPublicDatabaseFilter filter) {
        return ok(iPubFlowsService.copy(filter));
    }
}
