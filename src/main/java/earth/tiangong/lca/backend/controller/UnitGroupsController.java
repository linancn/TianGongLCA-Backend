package earth.tiangong.lca.backend.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import earth.tiangong.lca.backend.entity.UnitGroups;
import earth.tiangong.lca.backend.entity.UnitGroupsView;
import earth.tiangong.lca.backend.entity.UnitsJson;
import earth.tiangong.lca.backend.model.GridData;
import earth.tiangong.lca.backend.model.CopyFromOtherProjectsFilter;
import earth.tiangong.lca.backend.model.UnitGroupsViewGridFilter;
import earth.tiangong.lca.backend.model.UnitsJsonGridFilter;
import earth.tiangong.lca.backend.service.IUnitGroupsService;
import earth.tiangong.lca.backend.service.IUnitGroupsViewService;
import earth.tiangong.lca.backend.service.IUnitsJsonService;
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
@RequestMapping("/unitgroup")
public class UnitGroupsController {

    @Autowired
    private IUnitGroupsService iUnitGroupsService;

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody UnitGroups data) {
        return ok(iUnitGroupsService.create(data));
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody UnitGroups data) {
        return ok(iUnitGroupsService.updateBase(data));
    }

    @PutMapping("/copy")
    public ResponseEntity<String> copy(@RequestBody CopyFromOtherProjectsFilter filter) {
        return ok(iUnitGroupsService.copy(filter));
    }

    @DeleteMapping("/delete/{pkid}")
    public ResponseEntity<String> delete(@PathVariable Long pkid) {
        return ok(iUnitGroupsService.removeById(pkid) == true ? "ok" : "err");
    }

    @PutMapping("/updatecategory")
    public ResponseEntity<String> updateCategory(@RequestBody UnitGroups data) {
        return ok(iUnitGroupsService.updateCategory(data));
    }

    @PostMapping("/createunitjson")
    public ResponseEntity<String> createUnitJson(@RequestBody UnitsJson data) {
        return ok(iUnitGroupsService.updateUnitJson(data, "add"));
    }

    @PutMapping("/updateunitjson")
    public ResponseEntity<String> updateUnitJson(@RequestBody UnitsJson data) {
        return ok(iUnitGroupsService.updateUnitJson(data, "edit"));
    }

    @DeleteMapping("/deleteunitjson")
    public ResponseEntity<String> deletePropertyJson(@RequestBody UnitsJson data) {
        return ok(iUnitGroupsService.updateUnitJson(data, "delete"));
    }

    @Autowired
    private IUnitGroupsViewService iUnitGroupsViewService;

    @GetMapping("/get/{pkid}")
    public ResponseEntity<UnitGroupsView> getByPkid(@PathVariable Long pkid) {
        return ok(iUnitGroupsViewService.getByPkid(pkid));
    }

    @GetMapping("/get/{projectId}/{id}")
    public ResponseEntity<UnitGroupsView> getByDataId(@PathVariable Long projectId, @PathVariable String id) {
        return ok(iUnitGroupsViewService.getByDataId(projectId, id));
    }

    @GetMapping("/getgrid")
    public ResponseEntity<GridData<UnitGroupsView>> getGrid(UnitGroupsViewGridFilter filter) throws Exception {
        filter = filter == null ? new UnitGroupsViewGridFilter() : filter;
        return ok(iUnitGroupsViewService.getGrid(filter));
    }

    @Autowired
    private IUnitsJsonService iUnitsJsonService;

    @GetMapping("/getunitjsongrid")
    public ResponseEntity<GridData<UnitsJson>> getPropertyJsonViewGrid(UnitsJsonGridFilter filter) throws Exception {
        filter = filter == null ? new UnitsJsonGridFilter() : filter;
        return ok(iUnitsJsonService.getGrid(filter));
    }

    @GetMapping("/getunitjson/{projectId}/{unitGroupId}/{id}")
    public ResponseEntity<UnitsJson> getPropertyJsonView(@PathVariable Long projectId, @PathVariable String unitGroupId, @PathVariable String id) {
        return ok(iUnitsJsonService.getByDataId(projectId, unitGroupId, id));
    }
}
