package com.bouihassan.videostreaming.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignUpRequest(
        String username,

        @Email(message = "Invalid email format")
        @NotBlank(message = "email can not be blank")
        String email,

        @NotBlank(message = "password can not be blank")
        @Size(min = 6,message = "password must be more than 6")
        String password
) {
}
