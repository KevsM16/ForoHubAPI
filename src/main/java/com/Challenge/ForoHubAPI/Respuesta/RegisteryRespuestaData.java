package com.Challenge.ForoHubAPI.Respuesta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisteryRespuestaData(
        @NotBlank
        String solucion,
        @NotNull
        Long topicoId) {
}
