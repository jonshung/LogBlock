package com.logblock.backend.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class IndexController {
    
    @GetMapping("/")
    public String home( HttpServletRequest request) {
        return "";
    }

    @GetMapping("/error")
    public String error() {

        return "";
    }
}
