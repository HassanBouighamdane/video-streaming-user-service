package com.bouihassan.videostreaming.repositories;

import com.bouihassan.videostreaming.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
