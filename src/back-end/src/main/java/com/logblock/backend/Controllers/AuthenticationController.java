package com.logblock.backend.Controllers;

import com.logblock.backend.AuthenticationService.OAuthAuthenticator;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private OAuthAuthenticator oauthAuthenticator;

    /**
     * OAuth 2.0 Authentication with Google.
     *
     * @param request  The HTTP request
     * @param response The HTTP response
     * @return HTTP response with the authentication result
     */
    @PostMapping("/oauth")
    public ResponseEntity<?> OAuthAuthorizing(HttpServletRequest request, HttpServletResponse response) {
        return oauthAuthenticator.authenticate(request, response);
    }
}
