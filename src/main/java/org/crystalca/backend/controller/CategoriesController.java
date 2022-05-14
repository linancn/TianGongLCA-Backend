package org.crystalca.backend.controller;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.crystalca.backend.entity.Categories;
import org.crystalca.backend.model.CategoriesGridFilter;
import org.crystalca.backend.model.GridData;
import org.crystalca.backend.service.ICategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import static org.springframework.http.ResponseEntity.ok;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author CrystaLCA
 * @since 2022-02-17
 */
@Controller
@RequestMapping("/category")
public class CategoriesController {
    @Autowired
    private ICategoriesService iCategoriesService;

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody Categories data) {
        return ok(iCategoriesService.create(data));
    }

    @DeleteMapping("/delete/{pkid}")
    public ResponseEntity<String> delete(@PathVariable Long pkid) {
        return ok(iCategoriesService.removeById(pkid) == true ? "ok" : "err");
    }
    
    @PutMapping("/update")
    public ResponseEntity<?> updateInfo(@RequestBody Categories data) {
        return ok(iCategoriesService.update(data));
    }

    @GetMapping("/get/{pkid}")
    public ResponseEntity<Categories> getByPkid(@PathVariable Long pkid) {
        return ok(iCategoriesService.getByPkid(pkid));
    }

    @GetMapping("/get/{projectId}/{id}")
    public ResponseEntity<Categories> getByDataId(@PathVariable Long projectId, @PathVariable String id) {
        return ok(iCategoriesService.getByDataId(projectId, id));
    }

    @GetMapping("/getgrid")
    public ResponseEntity<GridData<Categories>> getbaseGrid(CategoriesGridFilter filter) throws Exception {
        filter = filter == null ? new CategoriesGridFilter() : filter;
        return ok(iCategoriesService.getGrid(filter));
    }
}
