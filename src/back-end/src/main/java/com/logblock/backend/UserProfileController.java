package com.logblock.backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserProfileController {
    
    @GetMapping("/db/get")
    public String getUser() {
        return "Hi\n";
    }
}
