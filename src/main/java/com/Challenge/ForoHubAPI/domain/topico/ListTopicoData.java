package com.Challenge.ForoHubAPI.domain.topico;

import com.Challenge.ForoHubAPI.Respuesta.ResponseRespuestaData;
import com.Challenge.ForoHubAPI.Respuesta.Respuesta;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record ListTopicoData(Long id, String titulo, String idUsuario, LocalDateTime fechaDeCreacion, String mensaje, String autor, String curso, boolean abierto,
                             List<ResponseRespuestaData> respuestas) {
    public ListTopicoData(Topico topico){
        this(topico.getId(), topico.getTitulo(),topico.getIdUsuario(),topico.getFechaDeCreacion(),topico.getMensaje(),topico.getAutor(),topico.getCurso(),topico.isAbierto(),topico.getRespuestas().stream().map(ResponseRespuestaData::new).collect(Collectors.toList()));
    }
}
