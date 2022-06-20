package earth.tiangong.lca.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/home")
public class HomeController {
    @GetMapping("/ok")
    public ResponseEntity<String> getOk() {
        return ok("ok");
    }
}