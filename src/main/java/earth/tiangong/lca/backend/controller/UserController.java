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

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private SysLoginService loginService;
    
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody)
    {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = loginService.login(loginBody.getUserName(), loginBody.getPassword());
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }
    
    @PostMapping("/current")
    public ResponseEntity<SysUsers> current() {
        SysUsers user = new SysUsers();
        user.setName("admin");
        return ok(user);
    }
}
