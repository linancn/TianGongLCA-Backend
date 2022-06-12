package earth.tiangong.lca.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import static org.springframework.http.ResponseEntity.ok;
import earth.tiangong.lca.backend.model.User;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author TianGongLCA
 * @since 2022-06-02
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @PutMapping("/login")
    public ResponseEntity<String> login(@RequestBody User data) {
        if (data.getUsername().equals("admin") && data.getPassword().equals("tiangonglca")) {
            return ok("ok");
        } else {
            return ok("error");
        }
    }

    @GetMapping("/currentUser")
    public ResponseEntity<User> fetchCurrentUser() {
        User user = new User();
        user.setName("Admin");
        return ok(user);
    }
}
