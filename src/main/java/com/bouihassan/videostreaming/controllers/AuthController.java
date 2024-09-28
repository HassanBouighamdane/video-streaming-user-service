package com.bouihassan.videostreaming.controllers;

import com.bouihassan.videostreaming.constants.Paths;
import com.bouihassan.videostreaming.dtos.requests.SignInRequest;
import com.bouihassan.videostreaming.dtos.requests.SignUpRequest;
import com.bouihassan.videostreaming.dtos.responses.SignInResponse;
import com.bouihassan.videostreaming.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Paths.AUTH_ROOT)
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(Paths.REGISTER)
    public ResponseEntity<Void> register(@RequestBody SignUpRequest signUpRequest) {
        authService.signup(signUpRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping(Paths.LOGIN)
    public SignInResponse login(@RequestBody SignInRequest signInRequest) {
        return authService.login(signInRequest);
    }

}
