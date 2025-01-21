package com.Challenge.ForoHubAPI.Validaciones;

import com.Challenge.ForoHubAPI.domain.topico.ITopicoRepository;
import com.Challenge.ForoHubAPI.domain.topico.UpdatePosts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateTopicsDuplicatedUpdate implements ValidadorTopicos<UpdatePosts>  {

    @Autowired
    ITopicoRepository topicoRepository;

    public void validacion(UpdatePosts data){
        var titulo=data.titulo().replaceAll("\\s+","").toLowerCase();
        var mensaje= data.mensaje().replaceAll("\\s+","").toLowerCase();
        var topicoExistente= topicoRepository.existsByTituloAndMensaje(titulo,mensaje);

        if(topicoExistente){
            throw new ValidacionException("Ya existe un topico con el nombre y mensaje colocado");
        }

    }
    }


