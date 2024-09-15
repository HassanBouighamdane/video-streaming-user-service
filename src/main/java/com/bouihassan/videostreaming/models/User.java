package com.bouihassan.videostreaming.models;

import com.bouihassan.videostreaming.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor @AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String userName;
    private String password;
    private String email;
    private String imageUrl;
    @Enumerated(EnumType.STRING)
    private Role role;
}
