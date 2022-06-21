package earth.tiangong.lca.backend.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import static org.springframework.http.ResponseEntity.ok;

import earth.tiangong.lca.backend.entity.Projects;
import earth.tiangong.lca.backend.model.GridData;
import earth.tiangong.lca.backend.model.ProjectGridFilter;
import earth.tiangong.lca.backend.service.IProjectsService;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 前端控制器
 * </p>
 * 
 * @author TianGongLCA
 * @since 2022-01-07
 */

@RestController
@RequestMapping("/project")
public class ProjectsController {

    @Autowired
    private IProjectsService iProjectsService;

    @ApiOperation(value = "project/grid")
    @GetMapping("/count")
    public ResponseEntity<Long> getById() {
        Long c = (long) -1;
        try {
            c = iProjectsService.count();
            return ok(c);
        } catch (Exception e) {
            return ok(c);
        }
    }

    @ApiOperation(value = "project/grid")
    @GetMapping("/get/{id}")
    public ResponseEntity<Projects> getById(@PathVariable Long id) throws Exception {
        return ok(iProjectsService.getById(id));
    }

    @PreAuthorize("@ss.hasAnyRoles( 'admin,user' )")
    @ApiOperation(value = "project/grid")
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