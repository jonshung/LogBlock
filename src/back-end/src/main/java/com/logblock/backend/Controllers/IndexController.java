package com.logblock.backend.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    
    @GetMapping("/")
    public String home() {

        return "";
    }

    @GetMapping("/error")
    public String error() {

        return "";
    }
}
