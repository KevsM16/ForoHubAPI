package com.Challenge.ForoHubAPI.domain.topico;

import com.Challenge.ForoHubAPI.Respuesta.Respuesta;

import java.time.LocalDateTime;
import java.util.List;

public record ResponseTopicoData(Long id, String titulo, String idUsuario, String mensaje, LocalDateTime fechaDeCreacion, String autor, String curso, boolean abierto,
                                 List<Respuesta> respuestas) {
    public ResponseTopicoData(Topico post){
        this(post.getId(),post.getTitulo(),post.getIdUsuario(),post.getMensaje(),post.getFechaDeCreacion(),post.getAutor(),post.getCurso(),post.isAbierto(),post.getRespuestas());
    }
}
