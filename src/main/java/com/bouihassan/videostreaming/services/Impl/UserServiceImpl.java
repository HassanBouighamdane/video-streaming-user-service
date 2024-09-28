package com.bouihassan.videostreaming.services.Impl;

import com.bouihassan.videostreaming.models.User;
import com.bouihassan.videostreaming.repositories.UserRepository;
import com.bouihassan.videostreaming.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);
        if (user.isEmpty()) throw new UsernameNotFoundException("User not found");
        return user.get();
    }

    @Override
    public User getUserById(String id) {
        Optional<User> user=userRepository.findById(id);
        if(user.isEmpty()) return null ;
        return user.get();
    }

    @Transactional
    @Override
    public Void deleteUser(String id) {
        userRepository.deleteById(id);
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}
