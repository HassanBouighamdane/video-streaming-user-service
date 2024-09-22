package com.bouihassan.videostreaming.services.Impl;

import com.bouihassan.videostreaming.repositories.UserRepository;
import com.bouihassan.videostreaming.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;


}
