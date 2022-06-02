package org.crystalca.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import static org.springframework.http.ResponseEntity.ok;
import org.crystalca.backend.model.User;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author CrystaLCA
 * @since 2022-06-02
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @PutMapping("/login")
    public ResponseEntity<String> login(@RequestBody User data) {
        if (data.getUsername().equals("admin") && data.getPassword().equals("crystalca") && data.getType().equals("account")) {
            return ok("ok");
        } else {
            return ok("error");
        }
    }
}
