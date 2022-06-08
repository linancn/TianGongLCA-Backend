package org.tiangonglca.backend.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.http.ResponseEntity.ok;

import java.util.List;

import org.tiangonglca.backend.entity.ModelFlowsJsonView;
import org.tiangonglca.backend.entity.Plans;
import org.tiangonglca.backend.model.GridData;
import org.tiangonglca.backend.model.ModelFlowsJsonExtension;
import org.tiangonglca.backend.model.ModelFlowsJsonGridFilter;
import org.tiangonglca.backend.model.ModelFlowsJsonViewGridFilter;
import org.tiangonglca.backend.model.PlansGridFilter;
import org.tiangonglca.backend.model.TreeNode;
import org.tiangonglca.backend.model.PlansExtension;
import org.tiangonglca.backend.service.IModelFlowsJsonService;
import org.tiangonglca.backend.service.IModelFlowsJsonViewService;
import org.tiangonglca.backend.service.IModelNodesJsonService;
import org.tiangonglca.backend.service.IPlansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author TianGongLCA
 * @since 2022-01-17
 */

@Controller
@RequestMapping("/plan")
public class PlansController {

    @Autowired
    private IPlansService iPlansService;

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody Plans data) {
        return ok(iPlansService.create(data));
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody Plans data) {
        return ok(iPlansService.updateBase(data));
    }

    @DeleteMapping("/delete/{pkid}")
    public ResponseEntity<String> delete(@PathVariable Long pkid) {
        return ok(iPlansService.removeById(pkid) == true ? "ok" : "err");
    }

    @GetMapping("/get/{pkid}")
    public ResponseEntity<Plans> get(@PathVariable Long pkid) {
        return ok(iPlansService.getBaseByPkid(pkid));
    }

    @GetMapping("/get/{projectId}/{id}")
    public ResponseEntity<Plans> get(@PathVariable Long projectId, @PathVariable String id) {
        return ok(iPlansService.getBaseByDataId(projectId, id));
    }

    @GetMapping("/getmodelcells/{projectId}/{id}")
    public ResponseEntity<PlansExtension> getModelCells(@PathVariable Long projectId, @PathVariable String id) {
        return ok(iPlansService.getModelCells(projectId, id));
    }

    @PutMapping("/updatemodelcells")
    public ResponseEntity<String> updateModelCells(@RequestBody Plans data) {
        return ok(iPlansService.updateModelCells(data.getProjectId(), data.getId(), data.getModelCells()));
    }

    @PostMapping("/createmodelflow")
    public ResponseEntity<String> createModelFlow(@RequestBody ModelFlowsJsonView data) {
        return ok(iPlansService.updateModelFlows(data,"add"));
    }

    @PutMapping("/updatemodelflow")
    public ResponseEntity<String> updateModelFlow(@RequestBody ModelFlowsJsonView data) {
        return ok(iPlansService.updateModelFlows(data,"edit"));
    }

    @DeleteMapping("/deletemodelflow")
    public ResponseEntity<String> deleteModelFlow(@RequestBody ModelFlowsJsonView data) {
        return ok(iPlansService.updateModelFlows(data,"delete"));
    }

    @DeleteMapping("/deletemodelflowbyprocess")
    public ResponseEntity<String> deleteModelFlowByProcess(@RequestBody ModelFlowsJsonView data) {
        return ok(iPlansService.updateModelFlows(data,"deleteByProcess"));
    }

    @GetMapping("/getgrid")
    public ResponseEntity<GridData<Plans>> getGrid(PlansGridFilter filter) throws Exception {
        filter = filter == null ? new PlansGridFilter() : filter;
        return ok(iPlansService.getGrid(filter));
    }

    @GetMapping("/getparentgrid")
    public ResponseEntity<GridData<Plans>> getParentGrid(PlansGridFilter filter) throws Exception {
        filter = filter == null ? new PlansGridFilter() : filter;
        return ok(iPlansService.getParentGrid(filter));
    }

    @Autowired
    private IModelFlowsJsonViewService iModelFlowsJsonViewService;

    @PostMapping("/getmodelflowgrid")
    public ResponseEntity<GridData<ModelFlowsJsonView>> getModelFlowGrid(@RequestBody ModelFlowsJsonViewGridFilter filter) throws Exception {
        filter = filter == null ? new ModelFlowsJsonViewGridFilter() : filter;
        return ok(iModelFlowsJsonViewService.getGrid(filter));
    }

    @Autowired
    private IModelFlowsJsonService iModelFlowsJsonService;

    @PostMapping("/getmodelprocessgrid")
    public ResponseEntity<GridData<ModelFlowsJsonExtension>> getModelProcessGrid(@RequestBody ModelFlowsJsonGridFilter filter) throws Exception {
        filter = filter == null ? new ModelFlowsJsonGridFilter() : filter;
        return ok(iModelFlowsJsonService.getGrid(filter));
    }

    @Autowired
    private IModelNodesJsonService iModelNodesJsonService;

    @GetMapping("/getmodelnodetree/{projectId}/{id}")
    public ResponseEntity<List<TreeNode>> getModelNodeTree(@PathVariable Long projectId, @PathVariable String id) {
        return ok(iModelNodesJsonService.getTree(projectId, id, "0"));
    }
}
