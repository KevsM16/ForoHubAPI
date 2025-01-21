package com.Challenge.ForoHubAPI.service;

import com.Challenge.ForoHubAPI.Validaciones.ValidacionException;
import com.Challenge.ForoHubAPI.Validaciones.ValidadorTopicos;
import com.Challenge.ForoHubAPI.domain.topico.*;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopicoService {

    @Autowired
    ITopicoRepository topicoRepository;

    @Autowired
     List <ValidadorTopicos<RegisteryTopicoData>> validadorTopicos;
    @Autowired
    List<ValidadorTopicos<UpdatePosts>> validadorTopicosActualizacion;

    public ResponseTopicoData crear(RegisteryTopicoData data){
           //validar
        validadorTopicos.forEach(v ->v.validacion(data));
            Topico topico =topicoRepository.save(new Topico(data));
            return new ResponseTopicoData(topico);
        }

    public Page<ListTopicoData> listado(Pageable paginacion) {
        return topicoRepository.findAll(paginacion).map(ListTopicoData::new);
    }

    public  ResponseEntity<ResponseTopicoData> obtenerTopicoEspecifico(@PathVariable Long id){

        Topico topico= topicoRepository.getReferenceById(id);

        if(!topicoRepository.existsById(id)){
            throw  new ValidacionException("Asegurate de colocar el id correctamente");
        }
        return  ResponseEntity.ok( new ResponseTopicoData(topico));

    }

    public Page<ListTopicoData>listadoPorFecha(Pageable paginacion){
          return topicoRepository.listarPorFecha(paginacion).map(ListTopicoData::new);
    }

    public List<ListTopicoData>listadoPorBusqueda( String curso, LocalDateTime fecha){
        return topicoRepository.listarPorBusqueda(curso,fecha).stream().map(ListTopicoData::new).toList();
    }

    @Transactional
    public ResponseTopicoData actualizar(@RequestBody @Valid UpdatePosts datos, Long id) {

        var idBuscado=topicoRepository.findById(id);
        if(!idBuscado.isPresent()){
            throw new ValidacionException("ID no existente o escrito incorrectamente");
        }else{
        //validacion
            validadorTopicosActualizacion.forEach(v ->v.validacion(datos));
            Topico topico= topicoRepository.getReferenceById(id);
            topico.actualizarTopicos(datos);
            return new ResponseTopicoData(topico);
        }
        }

    public void eliminar( @PathVariable Long id) {

        var idExistente=topicoRepository.findById(id);
        if(!idExistente.isPresent()){
            throw new ValidacionException("ID no existente o escrito incorrectamente");
        }
           topicoRepository.deleteById(id);
    }

}


