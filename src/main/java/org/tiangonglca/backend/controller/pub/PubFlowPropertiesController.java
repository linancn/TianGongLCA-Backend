package org.tiangonglca.backend.controller.pub;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tiangonglca.backend.entity.pub.PubFlowProperties;
import org.tiangonglca.backend.model.GridData;
import org.tiangonglca.backend.model.pub.PubFlowPropertiesGridFilter;
import org.tiangonglca.backend.model.pub.CopyFromPublicDatabaseFilter;
import org.tiangonglca.backend.service.pub.IPubFlowPropertiesService;
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
