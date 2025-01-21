package com.Challenge.ForoHubAPI.domain.users;

import jakarta.validation.constraints.NotBlank;

public record AuthenticateUserData(
        @NotBlank
        String login,
        @NotBlank
        String clave
) {
}
