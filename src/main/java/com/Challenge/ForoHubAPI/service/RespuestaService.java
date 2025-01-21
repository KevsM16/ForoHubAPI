package com.Challenge.ForoHubAPI.service;


import com.Challenge.ForoHubAPI.Respuesta.IRespuestaRepository;
import com.Challenge.ForoHubAPI.Respuesta.RegisteryRespuestaData;
import com.Challenge.ForoHubAPI.Respuesta.ResponseRespuestaData;
import com.Challenge.ForoHubAPI.Respuesta.Respuesta;
import com.Challenge.ForoHubAPI.domain.topico.ITopicoRepository;
import com.Challenge.ForoHubAPI.domain.topico.ListTopicoData;
import com.Challenge.ForoHubAPI.domain.topico.Topico;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RespuestaService {

    @Autowired
    ITopicoRepository topicoRepository;

    @Autowired
    IRespuestaRepository respuestaRepository;

     public ResponseRespuestaData crear(RegisteryRespuestaData data){

         if(!topicoRepository.existsById(data.topicoId())){
             System.out.println("no existe algun topico con este id ");
         }
         Topico topico=topicoRepository.findById(data.topicoId()).get();
         Respuesta respuesta=new Respuesta(null,topico, LocalDateTime.now(), data.solucion());
         respuestaRepository.save(respuesta);
         return new ResponseRespuestaData(respuesta);
     }

}
