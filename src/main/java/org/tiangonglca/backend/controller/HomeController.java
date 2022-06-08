package org.tiangonglca.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller()
@RequestMapping("/home")
public class HomeController {

    @GetMapping("/ok")
    public String getOk() {
        return "ok";
    }
}