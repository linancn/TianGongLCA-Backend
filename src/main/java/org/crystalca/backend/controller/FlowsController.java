package org.crystalca.backend.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.crystalca.backend.entity.FlowPropertiesJson;
import org.crystalca.backend.entity.FlowPropertiesJsonView;
import org.crystalca.backend.entity.Flows;
import org.crystalca.backend.entity.FlowsView;
import org.crystalca.backend.model.FlowPropertiesJsonViewGridFilter;
import org.crystalca.backend.model.FlowsViewGridFilter;
import org.crystalca.backend.model.GridData;
import org.crystalca.backend.model.CopyFromOtherProjectsFilter;
import org.crystalca.backend.service.IFlowPropertiesJsonViewService;
import org.crystalca.backend.service.IFlowsService;
import org.crystalca.backend.service.IFlowsViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import static org.springframework.http.ResponseEntity.ok;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author CrystaLCA
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
