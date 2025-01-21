package com.Challenge.ForoHubAPI.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ITopicoRepository extends JpaRepository<Topico,Long> {
        @Query("SELECT CASE WHEN COUNT(p) > 0 THEN TRUE ELSE FALSE END FROM Topico p WHERE LOWER(REPLACE(TRIM(p.titulo),' ', ''))=:titulo AND LOWER(REPLACE(TRIM(p.mensaje),' ', '')) =:mensaje")
        boolean existsByTituloAndMensaje(String titulo,String mensaje);

        boolean existsByIdUsuario(String idUsuario);


        @Query("SELECT t FROM Topico t ORDER BY t.fechaDeCreacion")
        Page<Topico>listarPorFecha(Pageable paginacion);

        @Query("SELECT t FROM Topico t WHERE t.curso ILIKE %:curso% OR t.fechaDeCreacion=:fecha")
        List<Topico> listarPorBusqueda(String curso, LocalDateTime fecha);

        Optional<Topico>findById(Long id);

}

