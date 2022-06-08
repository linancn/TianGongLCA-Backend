package org.tiangonglca.backend.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.tiangonglca.backend.entity.Locations;
import org.tiangonglca.backend.model.GridData;
import org.tiangonglca.backend.model.LocationsGridFilter;
import org.tiangonglca.backend.service.ILocationsService;
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
@RequestMapping("/location")
public class LocationsController {
    @Autowired
    private ILocationsService iLocationsService;

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody Locations data) {
        return ok(iLocationsService.create(data));
    }

    @DeleteMapping("/delete/{pkid}")
    public ResponseEntity<String> delete(@PathVariable Long pkid) {
        return ok(iLocationsService.removeById(pkid) == true ? "ok" : "err");
    }
    
    @PutMapping("/update")
    public ResponseEntity<?> updateInfo(@RequestBody Locations data) {
        return ok(iLocationsService.update(data));
    }

    @GetMapping("/get/{pkid}")
    public ResponseEntity<Locations> getByPkid(@PathVariable Long pkid) {
        return ok(iLocationsService.getByPkid(pkid));
    }

    @GetMapping("/get/{projectId}/{id}")
    public ResponseEntity<Locations> getByDataId(@PathVariable Long projectId, @PathVariable String id) {
        return ok(iLocationsService.getByDataId(projectId, id));
    }

    @GetMapping("/getgrid")
    public ResponseEntity<GridData<Locations>> getbaseGrid(LocationsGridFilter filter) throws Exception {
        filter = filter == null ? new LocationsGridFilter() : filter;
        return ok(iLocationsService.getGrid(filter));
    }
}
