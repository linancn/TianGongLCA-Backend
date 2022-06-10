package earth.tiangong.lca.backend.controller.pub;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import earth.tiangong.lca.backend.entity.pub.PubProcesses;
import earth.tiangong.lca.backend.model.GridData;
import earth.tiangong.lca.backend.model.pub.PubProcessesGridFilter;
import earth.tiangong.lca.backend.model.pub.CopyFromPublicDatabaseFilter;
import earth.tiangong.lca.backend.service.pub.IPubProcessesService;
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
