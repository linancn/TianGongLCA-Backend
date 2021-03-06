package earth.tiangong.lca.backend.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import earth.tiangong.lca.backend.entity.ExchangesJson;
import earth.tiangong.lca.backend.entity.ExchangesJsonView;
import earth.tiangong.lca.backend.entity.ParametersJson;
import earth.tiangong.lca.backend.entity.Processes;
import earth.tiangong.lca.backend.entity.ProcessesView;
import earth.tiangong.lca.backend.model.CopyFromOtherProjectsFilter;
import earth.tiangong.lca.backend.model.ExchangesJsonViewGridFilter;
import earth.tiangong.lca.backend.model.GridData;
import earth.tiangong.lca.backend.model.ParametersJsonGridFilter;
import earth.tiangong.lca.backend.model.ProcessesGridFilter;
import earth.tiangong.lca.backend.service.IExchangesJsonViewService;
import earth.tiangong.lca.backend.service.IParametersJsonService;
import earth.tiangong.lca.backend.service.IProcessesService;
import earth.tiangong.lca.backend.service.IProcessesViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import static org.springframework.http.ResponseEntity.ok;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author TianGongLCA
 * @since 2022-03-13
 */
@Controller
@RequestMapping("/process")
public class ProcessesController {
    @Autowired
    private IProcessesService iProcessesService;

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody Processes data) {
        return ok(iProcessesService.create(data));
    }

    @PutMapping("/copy")
    public ResponseEntity<String> copy(@RequestBody CopyFromOtherProjectsFilter filter) {
        return ok(iProcessesService.copy(filter));
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateBase(@RequestBody Processes data) {
        return ok(iProcessesService.updateBase(data));
    }

    @DeleteMapping("/delete/{pkid}")
    public ResponseEntity<String> delete(@PathVariable Long pkid) {
        return ok(iProcessesService.removeById(pkid) == true ? "ok" : "err");
    }

    @PostMapping("/createparameterjson")
    public ResponseEntity<String> createParametersJson(@RequestBody ParametersJson data) {
        return ok(iProcessesService.updateParametersJson(data, "add"));
    }

    @PutMapping("/updateparameterjson")
    public ResponseEntity<String> updateParametersJson(@RequestBody ParametersJson data) {
        return ok(iProcessesService.updateParametersJson(data, "edit"));
    }

    @DeleteMapping("/deleteparameterjson")
    public ResponseEntity<String> deleteParametersJson(@RequestBody ParametersJson data) {
        return ok(iProcessesService.updateParametersJson(data, "delete"));
    }

    @PostMapping("/createexchangejson")
    public ResponseEntity<String> createExchangesJson(@RequestBody ExchangesJson data) {
        return ok(iProcessesService.updateExchangesJson(data, "add"));
    }

    @PutMapping("/updateexchangejson")
    public ResponseEntity<String> updateExchangesJson(@RequestBody ExchangesJson data) {
        return ok(iProcessesService.updateExchangesJson(data, "edit"));
    }

    @DeleteMapping("/deleteexchangejson")
    public ResponseEntity<String> deleteExchangesJson(@RequestBody ExchangesJson data) {
        return ok(iProcessesService.updateExchangesJson(data, "delete"));
    }

    @GetMapping("/getgrid")
    public ResponseEntity<GridData<Processes>> getGrid(ProcessesGridFilter filter) throws Exception {
        filter = filter == null ? new ProcessesGridFilter() : filter;
        return ok(iProcessesService.getGrid(filter));
    }

    @GetMapping("/get/{projectId}/{id}")
    public ResponseEntity<Processes> getByDataId(@PathVariable Long projectId, @PathVariable String id) {
        return ok(iProcessesService.getByDataId(projectId, id));
    }

    @Autowired
    private IProcessesViewService iProcessesViewService;

    @GetMapping("/get/{pkid}")
    public ResponseEntity<ProcessesView> getByPkid(@PathVariable Long pkid) {
        return ok(iProcessesViewService.getByPkid(pkid));
    }

    @Autowired
    private IParametersJsonService iParametersJsonService;

    @GetMapping("/getparameterjsongrid")
    public ResponseEntity<GridData<ParametersJson>> getParameterJsonGrid(ParametersJsonGridFilter filter) throws Exception {
        filter = filter == null ? new ParametersJsonGridFilter() : filter;
        return ok(iParametersJsonService.getGrid(filter));
    }

    @GetMapping("/getparameterjson/{projectId}/{processId}/{id}")
    public ResponseEntity<ParametersJson> getParameterJson(@PathVariable Long projectId, @PathVariable String processId, @PathVariable String id) {
        return ok(iParametersJsonService.getByDataId(projectId, processId, id));
    }

    @Autowired
    private IExchangesJsonViewService iExchangesJsonViewService;

    @GetMapping("/getexchangejsongrid")
    public ResponseEntity<GridData<ExchangesJsonView>> getExchangeJsonGrid(ExchangesJsonViewGridFilter filter) throws Exception {
        filter = filter == null ? new ExchangesJsonViewGridFilter() : filter;
        return ok(iExchangesJsonViewService.getGrid(filter));
    }

    @GetMapping("/getexchangejson/{projectId}/{processId}/{internalId}/{input}")
    public ResponseEntity<ExchangesJsonView> getExchangeJson(@PathVariable Long projectId, @PathVariable String processId, @PathVariable Long internalId, @PathVariable Boolean input) {
        return ok(iExchangesJsonViewService.getByDataId(projectId, processId, internalId, input));
    }
}
