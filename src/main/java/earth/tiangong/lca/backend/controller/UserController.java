package earth.tiangong.lca.backend.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import static org.springframework.http.ResponseEntity.ok;

import org.springframework.beans.factory.annotation.Autowired;

import earth.tiangong.lca.backend.entity.SysUsers;
import earth.tiangong.lca.backend.model.GridData;
import earth.tiangong.lca.backend.model.UserGridFilter;
import earth.tiangong.lca.backend.security.exception.AjaxResult;
import earth.tiangong.lca.backend.security.model.LoginBody;
import earth.tiangong.lca.backend.security.service.SysLoginService;
import earth.tiangong.lca.backend.security.util.Constants;
import earth.tiangong.lca.backend.security.util.SecurityUtils;
import earth.tiangong.lca.backend.service.ISysUsersService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private SysLoginService loginService;
    @Autowired
    private ISysUsersService iSysUsersService;

    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody) {
        try {
            // 生成令牌
            String token = loginService.login(loginBody.getUserName(), loginBody.getPassword());
            AjaxResult ajax = AjaxResult.success();
            ajax.put(Constants.TOKEN, token);
            return ajax;
        } catch (Exception e) {
            AjaxResult ajax = AjaxResult.error();
            return ajax;
        }
    }

    @PostMapping("/current")
    public ResponseEntity<SysUsers> current() {
        SysUsers sysUser = SecurityUtils.getLoginUser().getUser();
        SysUsers currentUser = new SysUsers();
        currentUser.setId(sysUser.getId());
        currentUser.setName(sysUser.getName());
        currentUser.setEmail(sysUser.getEmail());
        return ok(currentUser);
    }

    // @PreAuthorize("@ss.hasAnyRoles( 'admin,user' )")
    @ApiOperation(value = "grid")
    @GetMapping("/grid")
    public ResponseEntity<GridData<SysUsers>> getGrid(UserGridFilter filter) throws Exception {
        filter = filter == null ? new UserGridFilter() : filter;
        GridData<SysUsers> result = iSysUsersService.getGrid(filter);
        return ok(result);
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody SysUsers data) {
        return ok(iSysUsersService.create(data));
    }

    @PostMapping("/update")
    public ResponseEntity<String> update(@RequestBody SysUsers data) {
        return ok(iSysUsersService.update(data));
    }

    @PostMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody SysUsers data) {
        data.setIsDeleted(true);
        return ok(iSysUsersService.update(data));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<SysUsers> getByPkid(@PathVariable Long id) {
        return ok(iSysUsersService.getById(id));
    }

}
