package com.logblock.backend.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private Environment env;
    /**
     * OAuth 2.0 Authentication with Google.
     *
     * @param request  The HTTP request
     * @param response The HTTP response
     * @return HTTP response with the authentication result
     */
    @PostMapping("/oauth/google")
    public void OAuthAuthorizing(HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
        if(SecurityContextHolder.getContext().getAuthentication() != null &&
        SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            response.setHeader("Location", 
                "http://" + 
                env.getProperty("logblock.front-end-integration.hostname") + 
                ":" +
                env.getProperty("logblock.front-end-integration.port")
            );
        } else {
            response.setHeader("Location", "/oauth2/authorization/google");
        }
    }
}
