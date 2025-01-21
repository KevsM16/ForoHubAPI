package com.Challenge.ForoHubAPI.controller;

import com.Challenge.ForoHubAPI.Respuesta.RegisteryRespuestaData;
import com.Challenge.ForoHubAPI.Respuesta.ResponseRespuestaData;
import com.Challenge.ForoHubAPI.domain.topico.ListTopicoData;
import com.Challenge.ForoHubAPI.service.RespuestaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/respuestas")
@SecurityRequirement(name = "bearer-key")
public class RespuestaController {


    @Autowired
    RespuestaService respuestaService;

      @PostMapping
     @Transactional
    public  ResponseEntity<ResponseRespuestaData> postTopico(@RequestBody RegisteryRespuestaData data){

          ResponseRespuestaData responseRespuestaData=respuestaService.crear(data);
          return ResponseEntity.ok(responseRespuestaData);
      }








}
