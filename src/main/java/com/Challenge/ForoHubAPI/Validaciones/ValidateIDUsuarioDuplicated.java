package com.Challenge.ForoHubAPI.Validaciones;

import com.Challenge.ForoHubAPI.domain.topico.ITopicoRepository;
import com.Challenge.ForoHubAPI.domain.topico.RegisteryTopicoData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateIDUsuarioDuplicated implements ValidadorTopicos<RegisteryTopicoData> {

    @Autowired
    ITopicoRepository topicoRepository;

    public void validacion(RegisteryTopicoData data){
        boolean usuarioIdDuplicado= topicoRepository.existsByIdUsuario(data.idUsuario());

        if(usuarioIdDuplicado){
            throw new ValidacionException("ID usuario ya existente");
        }
    }

}
