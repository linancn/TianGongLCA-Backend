package earth.tiangong.lca.backend.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import static org.springframework.http.ResponseEntity.ok;

import org.springframework.beans.factory.annotation.Autowired;

import earth.tiangong.lca.backend.entity.SysUsers;
import earth.tiangong.lca.backend.security.exception.AjaxResult;
import earth.tiangong.lca.backend.security.model.LoginBody;
import earth.tiangong.lca.backend.security.service.SysLoginService;
import earth.tiangong.lca.backend.security.util.Constants;
import earth.tiangong.lca.backend.security.util.SecurityUtils;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private SysLoginService loginService;

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
}
