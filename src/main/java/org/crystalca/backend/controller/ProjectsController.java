package org.crystalca.backend.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import static org.springframework.http.ResponseEntity.ok;

import org.crystalca.backend.entity.Projects;
import org.crystalca.backend.model.GridData;
import org.crystalca.backend.model.ProjectGridFilter;
import org.crystalca.backend.service.IProjectsService;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author CrystaLCA
 * @since 2022-01-07
 */

@Controller()
@RequestMapping("/project")
public class ProjectsController {

    @Autowired
    private IProjectsService iProjectsService;

    @GetMapping("/get/{id}")
    public ResponseEntity<Projects> getById(@PathVariable Long id) throws Exception {
        return ok(iProjectsService.getById(id));
    }

    @GetMapping("/grid")
    public ResponseEntity<GridData<Projects>> getGrid(ProjectGridFilter filter) throws Exception {
        filter = filter == null ? new ProjectGridFilter() : filter;
        GridData<Projects> result = iProjectsService.getProjectGrid(filter);
        return ok(result);
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody Projects data) {
        return ok(iProjectsService.create(data));
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateInfo(@RequestBody Projects data) {
        return ok(iProjectsService.update(data));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return ok(iProjectsService.delete(id));
    }

    @PutMapping("/star/{id}")
    public ResponseEntity<?> star(@PathVariable Long id) {
        return ok(iProjectsService.star(id));
    }
}