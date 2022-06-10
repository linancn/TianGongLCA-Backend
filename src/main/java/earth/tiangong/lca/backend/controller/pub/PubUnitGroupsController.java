package earth.tiangong.lca.backend.controller.pub;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import earth.tiangong.lca.backend.entity.pub.PubUnitGroups;
import earth.tiangong.lca.backend.model.GridData;
import earth.tiangong.lca.backend.model.pub.PubUnitGroupsGridFilter;
import earth.tiangong.lca.backend.model.pub.CopyFromPublicDatabaseFilter;
import earth.tiangong.lca.backend.service.pub.IPubUnitGroupsService;
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
