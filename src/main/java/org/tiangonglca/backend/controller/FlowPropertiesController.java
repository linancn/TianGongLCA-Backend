package org.tiangonglca.backend.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tiangonglca.backend.entity.FlowProperties;
import org.tiangonglca.backend.entity.FlowPropertiesView;
import org.tiangonglca.backend.model.FlowPropertiesViewGridFilter;
import org.tiangonglca.backend.model.GridData;
import org.tiangonglca.backend.model.CopyFromOtherProjectsFilter;
import org.tiangonglca.backend.service.IFlowPropertiesService;
import org.tiangonglca.backend.service.IFlowPropertiesViewService;
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
 * @since 2022-02-17
 */
@Controller
@RequestMapping("/flowproperty")
public class FlowPropertiesController {
    @Autowired
    private IFlowPropertiesService iFlowPropertiesService;

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody FlowProperties data) {
        return ok(iFlowPropertiesService.create(data));
    }

    @DeleteMapping("/delete/{pkid}")
    public ResponseEntity<String> delete(@PathVariable Long pkid) {
        return ok(iFlowPropertiesService.removeById(pkid) == true ? "ok" : "err");
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateInfo(@RequestBody FlowProperties data) {
        return ok(iFlowPropertiesService.updateBase(data));
    }

    @PutMapping("/copy")
    public ResponseEntity<String> copy(@RequestBody CopyFromOtherProjectsFilter filter) {
        return ok(iFlowPropertiesService.copy(filter));
    }

    @PutMapping("/updatecategory")
    public ResponseEntity<String> updateCategory(@RequestBody FlowProperties data) {
        return ok(iFlowPropertiesService.updateCategory(data));
    }

    @PutMapping("/updateunitgroup")
    public ResponseEntity<String> updateUnitGroup(@RequestBody FlowProperties data) {
        return ok(iFlowPropertiesService.updateUnitGroup(data));
    }

    @Autowired
    private IFlowPropertiesViewService iFlowPropertiesViewService;

    @GetMapping("/get/{projectId}/{id}")
    public ResponseEntity<FlowPropertiesView> getByDataId(@PathVariable Long projectId, @PathVariable String id) {
        return ok(iFlowPropertiesViewService.getByDataId(projectId, id));
    }

    @GetMapping("/get/{pkid}")
    public ResponseEntity<FlowPropertiesView> getByPkid(@PathVariable Long pkid) {
        return ok(iFlowPropertiesViewService.getByPkid(pkid));
    }

    @GetMapping("/getgrid")
    public ResponseEntity<GridData<FlowPropertiesView>> getGrid(FlowPropertiesViewGridFilter filter) throws Exception {
        filter = filter == null ? new FlowPropertiesViewGridFilter() : filter;
        return ok(iFlowPropertiesViewService.getGrid(filter));
    }
}
