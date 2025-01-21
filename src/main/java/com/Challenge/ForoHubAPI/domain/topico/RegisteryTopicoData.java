package com.Challenge.ForoHubAPI.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisteryTopicoData(

@NotBlank
String titulo,

@NotBlank
String idUsuario,
@NotBlank
String mensaje,
@NotBlank
String autor,
@NotBlank
String curso

) {
}
