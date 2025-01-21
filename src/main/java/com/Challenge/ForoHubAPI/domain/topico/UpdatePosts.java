package com.Challenge.ForoHubAPI.domain.topico;

import jakarta.validation.constraints.NotNull;

public record UpdatePosts(
        @NotNull
        String titulo,
        @NotNull
        String mensaje,
        @NotNull
        String autor,
        @NotNull
        String curso) {
}
