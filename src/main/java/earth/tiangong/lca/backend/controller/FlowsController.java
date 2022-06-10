package earth.tiangong.lca.backend.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestBody;
import earth.tiangong.lca.backend.entity.FlowPropertiesJson;
import earth.tiangong.lca.backend.entity.FlowPropertiesJsonView;
import earth.tiangong.lca.backend.entity.Flows;
import earth.tiangong.lca.backend.entity.FlowsView;
import earth.tiangong.lca.backend.model.FlowPropertiesJsonViewGridFilter;
import earth.tiangong.lca.backend.model.FlowsViewGridFilter;
import earth.tiangong.lca.backend.model.GridData;
import earth.tiangong.lca.backend.model.CopyFromOtherProjectsFilter;
import earth.tiangong.lca.backend.service.IFlowPropertiesJsonViewService;
import earth.tiangong.lca.backend.service.IFlowsService;
import earth.tiangong.lca.backend.service.IFlowsViewService;
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
 * @since 2022-02-13
 */

@Controller
@RequestMapping("/flow")
public class FlowsController {
    @Autowired
    private IFlowsService iFlowsService;

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody Flows data) {
        return ok(iFlowsService.create(data));
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody Flows data) {
        return ok(iFlowsService.updateBase(data));
    }

    @PutMapping("/copy")
    public ResponseEntity<String> copy(@RequestBody CopyFromOtherProjectsFilter filter) {
        return ok(iFlowsService.copy(filter));
    }

    @DeleteMapping("/delete/{pkid}")
    public ResponseEntity<String> delete(@PathVariable Long pkid) {
        return ok(iFlowsService.removeById(pkid) == true ? "ok" : "err");
    }

    @PutMapping("/updatelocation")
    public ResponseEntity<String> updateLocation(@RequestBody Flows data) {
        return ok(iFlowsService.updateLocation(data));
    }

    @PutMapping("/updatecategory")
    public ResponseEntity<String> updateCategory(@RequestBody Flows data) {
        return ok(iFlowsService.updateCategory(data));
    }

    @PostMapping("/createpropertyjson")
    public ResponseEntity<String> createPropertyJson(@RequestBody FlowPropertiesJson data) {
        return ok(iFlowsService.updatePropertyJson(null, data, "add"));
    }

    @PutMapping("/updatepropertyjson/{propertyId}")
    public ResponseEntity<String> updatePropertyJson(@PathVariable String propertyId, @RequestBody FlowPropertiesJson data) {
        return ok(iFlowsService.updatePropertyJson(propertyId, data, "edit"));
    }

    @DeleteMapping("/deletepropertyjson")
    public ResponseEntity<String> deletePropertyJson(@RequestBody FlowPropertiesJson data) {
        return ok(iFlowsService.updatePropertyJson(data.getFlowPropertyId(), data, "delete"));
    }

    @Autowired
    private IFlowsViewService iFlowsViewService;

    @GetMapping("/get/{pkid}")
    public ResponseEntity<FlowsView> getByPkid(@PathVariable Long pkid) {
        return ok(iFlowsViewService.getById(pkid));
    }

    @GetMapping("/get/{projectId}/{id}")
    public ResponseEntity<FlowsView> getByDataId(@PathVariable Long projectId, @PathVariable String id) {
        return ok(iFlowsViewService.getByDataId(projectId, id));
    }

    @GetMapping("/getgrid")
    public ResponseEntity<GridData<FlowsView>> getGrid(FlowsViewGridFilter filter) throws Exception {
        filter = filter == null ? new FlowsViewGridFilter() : filter;
        return ok(iFlowsViewService.getGrid(filter));
    }

    @Autowired
    private IFlowPropertiesJsonViewService iFlowPropertiesJsonViewService;

    @GetMapping("/getpropertyjsonviewgrid")
    public ResponseEntity<GridData<FlowPropertiesJsonView>> getPropertyJsonViewGrid(FlowPropertiesJsonViewGridFilter filter) throws Exception {
        filter = filter == null ? new FlowPropertiesJsonViewGridFilter() : filter;
        return ok(iFlowPropertiesJsonViewService.getGrid(filter));
    }

    @GetMapping("/getpropertyjsonview/{projectId}/{flowId}/{propertyId}")
    public ResponseEntity<FlowPropertiesJsonView> getPropertyJsonView(@PathVariable Long projectId,@PathVariable String flowId, @PathVariable String propertyId) {
        return ok(iFlowPropertiesJsonViewService.getByDataId(projectId, flowId, propertyId));
    }
}
