package com.logblock.backend.AuthenticationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class OAuthAuthenticator {

    @Autowired
    private AccountService accountService;

    /**
     * OAuth callback after successful authentication.
     *
     * @param request  HTTP request containing the OAuth response
     * @param response HTTP response after handling the callback
     * @return HTTP response after handling the OAuth callback and generating token
     */
    public ResponseEntity<?> oauthCallback(HttpServletRequest request, HttpServletResponse response) {
        // Extract authorization code from the request
        String authorizationCode = request.getParameter("code");

        if (authorizationCode == null || authorizationCode.isEmpty()) {
            return ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST)
                    .body("Authorization code missing");
        }

        // Exchange the authorization code for an access token
        String token = generateToken(authorizationCode);

        // Add token to response header
        response.setHeader("Authorization", "Bearer " + token);

        // Return a successful response
        return ResponseEntity.ok("Authentication successful. Token generated.");
    }

    /**
     * Authenticate profile by OAuth token.
     *
     * @param request  HTTP request containing OAuth credentials (e.g., token)
     * @param response HTTP response
     * @return ResponseEntity with authentication result
     */
    public ResponseEntity<?> authenticate(HttpServletRequest request, HttpServletResponse response) {
        // Retrieve the OAuth token from the request header
        String token = request.getHeader("Authorization");

        if (token == null || token.isEmpty()) {
            return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED)
                    .body("Missing or empty Authorization token");
        }

        // Strip the "Bearer " prefix to extract the token
        token = token.startsWith("Bearer ") ? token.substring(7) : token;

        // Verify the token using the actual OAuth provider or JWT
        boolean isAuthenticated = verifyToken(token);

        if (isAuthenticated) {
            return ResponseEntity.ok("Profile authenticated successfully");
        } else {
            return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED)
                    .body("Invalid or expired token");
        }
    }

    /**
     * Generate OAuth token based on authorization code.
     *
     * @param authorizationCode The authorization code obtained from the OAuth
     *                          provider
     * @return The OAuth token as a string
     */
    private String generateToken(String authorizationCode) {
        // Exchange the authorization code for an access token from OAuth provider
        // For example, we could make an API call to Google OAuth server to get the
        // token
        // Simplified for demonstration, this is where you would add the OAuth token
        // exchange logic.

        // Normally you would use a library or make a request to an OAuth provider to
        // exchange the code for a token
        return "GeneratedTokenBasedOn:" + authorizationCode;
    }

    /**
     * Verify the provided OAuth token.
     * This is a simplified version, in real-world scenarios, use a library to
     * verify JWT or call OAuth provider.
     *
     * @param token The OAuth token to be verified
     * @return true if the token is valid, otherwise false
     */
    private boolean verifyToken(String token) {
        // Example: If the token equals a valid token, return true
        // In real life, this would involve calling the OAuth provider or decoding a JWT
        return token.equals("ValidToken"); // Simulated check
    }
}
