package com.logblock.backend.Controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

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
        response.setHeader("Location", "/oauth2/authorization/google");
    }
}
