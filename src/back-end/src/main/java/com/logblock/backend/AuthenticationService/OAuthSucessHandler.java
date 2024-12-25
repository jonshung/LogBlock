package com.logblock.backend.AuthenticationService;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuthSucessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private AccountService accountService;

    @Autowired
    private Environment env;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        DefaultOidcUser user = (DefaultOidcUser) authentication.getPrincipal();
        
        if(!accountService.accountExistsByEmail(user.getEmail())) {
            accountService.createAccount(user.getEmail());
        }
        response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
        response.setHeader("Location", 
            env.getProperty("logblock.front-end-integration.hostname") + 
            ":" +
            env.getProperty("logblock.front-end-integration.port")
        );
        Cookie session_id = new Cookie("JSESSIONID", request.getSession().getId());
        session_id.setHttpOnly(true);
        response.addCookie(session_id);
    }
}
