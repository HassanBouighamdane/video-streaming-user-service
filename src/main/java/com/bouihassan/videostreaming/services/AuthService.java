package com.bouihassan.videostreaming.services;

import com.bouihassan.videostreaming.dtos.requests.SignInRequest;
import com.bouihassan.videostreaming.dtos.requests.SignUpRequest;

public interface AuthService {
    void signup(SignUpRequest signUpRequest);

    String login(SignInRequest signInRequest);
}
