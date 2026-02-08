package com.limnstage.laundry.controller;


import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.limnstage.laundry.security.JwtUtil;
import com.limnstage.laundry.services.AuthService;
import com.limnstage.laundry.services.GoogleTokenVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final GoogleTokenVerifier verifier;
    private final AuthService authService;

    // ✅ Constructor injection (recommended)
    public AuthController(GoogleTokenVerifier verifier, AuthService authService) {
        this.verifier = verifier;
        this.authService = authService;
    }

    @PostMapping("/google")
    public ResponseEntity<?> googleLogin(
            @RequestBody Map<String, String> body) {

        String idToken = body.get("idToken");
        if (idToken == null || idToken.isBlank()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "idToken is required"));
        }
        try {
            return ResponseEntity.ok(
                    authService.loginWithGoogle(idToken)
            );
        } catch (Exception e) {
            return ResponseEntity.status(401)
                    .body(Map.of("error", "Invalid Google token"));
        }
    }
}
