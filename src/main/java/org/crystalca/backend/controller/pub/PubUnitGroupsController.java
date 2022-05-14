package org.crystalca.backend.controller.pub;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.crystalca.backend.entity.pub.PubUnitGroups;
import org.crystalca.backend.model.GridData;
import org.crystalca.backend.model.pub.PubUnitGroupsGridFilter;
import org.crystalca.backend.model.pub.CopyFromPublicDatabaseFilter;
import org.crystalca.backend.service.pub.IPubUnitGroupsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import static org.springframework.http.ResponseEntity.ok;

@Controller
@RequestMapping("/pub/unitgroup")
public class PubUnitGroupsController {

    @Autowired
    private IPubUnitGroupsService iPubUnitGroupsService;

    @GetMapping("/getgrid")
    public ResponseEntity<GridData<PubUnitGroups>> getGrid(PubUnitGroupsGridFilter filter) throws Exception {
        filter = filter == null ? new PubUnitGroupsGridFilter() : filter;
        return ok(iPubUnitGroupsService.getGrid(filter));
    }

    @PutMapping("/copy")
    public ResponseEntity<String> copy(@RequestBody CopyFromPublicDatabaseFilter filter) {
        return ok(iPubUnitGroupsService.copy(filter));
    }
}
