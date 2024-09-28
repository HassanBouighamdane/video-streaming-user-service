package com.bouihassan.videostreaming.services;

import com.bouihassan.videostreaming.dtos.requests.SignInRequest;
import com.bouihassan.videostreaming.dtos.requests.SignUpRequest;
import com.bouihassan.videostreaming.dtos.responses.SignInResponse;

public interface AuthService {
    void signup(SignUpRequest signUpRequest);

    SignInResponse login(SignInRequest signInRequest);
}
