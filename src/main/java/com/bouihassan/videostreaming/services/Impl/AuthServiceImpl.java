package com.bouihassan.videostreaming.services.Impl;

import com.bouihassan.videostreaming.dtos.requests.SignInRequest;
import com.bouihassan.videostreaming.dtos.requests.SignUpRequest;
import com.bouihassan.videostreaming.exceptions.DuplicateException;
import com.bouihassan.videostreaming.models.User;
import com.bouihassan.videostreaming.repositories.UserRepository;
import com.bouihassan.videostreaming.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Transactional
    @Override
    public void signup(SignUpRequest signUpRequest) {

        Optional<User> existingUser=userRepository.findByEmail(signUpRequest.email());
        if(existingUser.isPresent()){
            throw new DuplicateException("Email already exists");
        }
        String hashPassword=passwordEncoder.encode(signUpRequest.password());
        User user=User.builder()
                .email(signUpRequest.email())
                .password(hashPassword)
                .userName(signUpRequest.username())
                .build();
        userRepository.save(user);
    }

    @Override
    public String login(SignInRequest signInRequest) {
        Authentication authentication=
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                signInRequest.email(), signInRequest.password()));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(signInRequest.email());
        }
        return "Invalid username or password";
    }
}
