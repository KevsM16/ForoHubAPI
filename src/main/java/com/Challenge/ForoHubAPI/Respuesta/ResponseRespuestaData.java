package com.Challenge.ForoHubAPI.Respuesta;

import java.time.LocalDateTime;

public record ResponseRespuestaData(Long id, Long idTopico, LocalDateTime fechaDeCreacion, String solucion) {

    public ResponseRespuestaData(Respuesta respuesta){
        this(respuesta.getId(),respuesta.getTopico().getId(),respuesta.getFechaCreacion(), respuesta.getSolucion());
    }
}
