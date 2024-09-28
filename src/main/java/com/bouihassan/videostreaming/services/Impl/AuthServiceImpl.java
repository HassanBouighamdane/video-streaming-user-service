package com.bouihassan.videostreaming.services.Impl;

import com.bouihassan.videostreaming.dtos.requests.SignInRequest;
import com.bouihassan.videostreaming.dtos.requests.SignUpRequest;
import com.bouihassan.videostreaming.dtos.responses.SignInResponse;
import com.bouihassan.videostreaming.exceptions.DuplicateException;
import com.bouihassan.videostreaming.models.User;
import com.bouihassan.videostreaming.repositories.UserRepository;
import com.bouihassan.videostreaming.services.AuthService;
import com.bouihassan.videostreaming.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Service

public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

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
    public SignInResponse login(SignInRequest signInRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signInRequest.email(),signInRequest.password()));

        final UserDetails userDetails = userDetailsService.loadUserByUsername(signInRequest.email());
        return new SignInResponse(
                jwtUtil.generateToken(userDetails),"refreshToken");
    }


}
