package org.tiangonglca.backend.controller.pub;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tiangonglca.backend.entity.pub.PubFlows;
import org.tiangonglca.backend.model.GridData;
import org.tiangonglca.backend.model.pub.PubFlowsGridFilter;
import org.tiangonglca.backend.model.pub.CopyFromPublicDatabaseFilter;
import org.tiangonglca.backend.service.pub.IPubFlowsService;
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
