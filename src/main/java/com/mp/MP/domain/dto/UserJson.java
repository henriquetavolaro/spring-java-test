package com.mp.MP.domain.dto;

import com.mp.MP.domain.models.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserJson(
        String id,
        @NotBlank
        String name,
        @NotBlank
        @Email
        String email

) {
    public UserJson(User user) {
        this(user.getId(), user.getName(), user.getEmail());
    }
}
