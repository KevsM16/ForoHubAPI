package com.Challenge.ForoHubAPI.controller;


import com.Challenge.ForoHubAPI.domain.topico.*;
import com.Challenge.ForoHubAPI.service.TopicoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    ITopicoRepository topicoRepository;

    @Autowired
    TopicoService topicoService;

    @PostMapping
    @Transactional
    public ResponseEntity<ResponseTopicoData> postTopico(@RequestBody @Valid RegisteryTopicoData data,UriComponentsBuilder uriComponentsBuilder){
            ResponseTopicoData responsePostData= topicoService.crear(data);
            URI url= uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(responsePostData.id()).toUri();
       return ResponseEntity.created(url).body(responsePostData);
    }

    @GetMapping
    public ResponseEntity<Page<ListTopicoData>>listadoTopicos(Pageable paginacion){
        return   ResponseEntity.ok(topicoService.listado(paginacion));

    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseTopicoData> topicoPorId(@PathVariable Long id){
          return topicoService.obtenerTopicoEspecifico(id);

    }

    @GetMapping("/fecha")
    public ResponseEntity<Page<ListTopicoData>>listadoTopicosPorFecha(Pageable paginacion){
        return ResponseEntity.ok(topicoService.listadoPorFecha(paginacion));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<ListTopicoData>>listadoporBusqueda(@RequestParam(required = false)String curso, @RequestParam(required = false) @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDateTime fecha){
        return ResponseEntity.ok(topicoService.listadoPorBusqueda(curso,fecha));
        }

        @PutMapping("/{id}")
        @Transactional
    public ResponseEntity actualizarPosts(@RequestBody @Valid UpdatePosts datos, @PathVariable Long id){

        return ResponseEntity.ok(topicoService.actualizar(datos,id));

        }
        @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarDatos(@PathVariable Long id){

             topicoService.eliminar(id);
            return ResponseEntity.noContent().build();
        }

}
