package com.bouihassan.videostreaming.services;


import com.bouihassan.videostreaming.models.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(String id);

    Void deleteUser(String id);
}
