package org.tiangonglca.backend.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tiangonglca.backend.entity.Parameter;
import org.tiangonglca.backend.model.GridData;
import org.tiangonglca.backend.model.ParameterGridFilter;
import org.tiangonglca.backend.service.IParameterService;
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
 * @since 2022-01-20
 */
@Controller
@RequestMapping("/parameter")
public class ParameterController {
    @Autowired
    private IParameterService iParameterService;

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody Parameter parameter) {
        return ok(iParameterService.create(parameter));
    }

    @GetMapping("/get/{projectId}/{processId}/{id}")
    public ResponseEntity<Parameter> getByDataId(@PathVariable Long projectId, @PathVariable String processId, @PathVariable String id) {
        return ok(iParameterService.getByDataId(projectId, processId, id));
    }

    // @GetMapping("/get/{pkid}")
    // public ResponseEntity<Parameter> getByPkid(@PathVariable Long pkid) {
    //     return ok(iParameterService.getById(pkid));
    // }

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody Parameter parameter) {
        return ok(iParameterService.update(parameter));
    }

    @DeleteMapping("/delete/{pkid}")
    public ResponseEntity<String> delete(@PathVariable Long pkid) {
        String result = iParameterService.delete(pkid);
        return ok(result);
    }

    @GetMapping("/getgrid")
    public ResponseEntity<GridData<Parameter>> getGrid(ParameterGridFilter filter) throws Exception {
        filter = filter == null ? new ParameterGridFilter() : filter;
        return ok(iParameterService.getGrid(filter));
    }
}
