package org.crystalca.backend.controller.pub;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.crystalca.backend.entity.pub.PubProcesses;
import org.crystalca.backend.model.GridData;
import org.crystalca.backend.model.pub.PubProcessesGridFilter;
import org.crystalca.backend.model.pub.CopyFromPublicDatabaseFilter;
import org.crystalca.backend.service.pub.IPubProcessesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import static org.springframework.http.ResponseEntity.ok;

@Controller
@RequestMapping("/pub/process")
public class PubProcessesController {

    @Autowired
    private IPubProcessesService iPubProcessesService;

    @GetMapping("/getgrid")
    public ResponseEntity<GridData<PubProcesses>> getGrid(PubProcessesGridFilter filter) throws Exception {
        filter = filter == null ? new PubProcessesGridFilter() : filter;
        return ok(iPubProcessesService.getGrid(filter));
    }

    @PutMapping("/copy")
    public ResponseEntity<String> copy(@RequestBody CopyFromPublicDatabaseFilter filter) {
        return ok(iPubProcessesService.copy(filter));
    }
}
