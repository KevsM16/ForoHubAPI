package com.Challenge.ForoHubAPI.Respuesta;


import com.Challenge.ForoHubAPI.domain.topico.Topico;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name= "respuestas")
@NoArgsConstructor

public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

   @ManyToOne
   @JoinColumn(name = "topico_id", nullable = false)
    Topico topico;

    LocalDateTime fechaDeCreacion;

    String solucion;

    public Topico getTopico() {
        return topico;
    }

    public void setTopico(Topico topico) {
        this.topico = topico;
    }

    public Long getId() {
        return id;
    }

    public String getSolucion() {
        return solucion;
    }

    public void setFechaCreacion() {
        this.fechaDeCreacion = LocalDateTime.now();
    }

    public LocalDateTime getFechaCreacion() {
        return fechaDeCreacion;
    }



    public Respuesta(Long id, Topico topico, LocalDateTime fechaCreacion, String solucion) {
        this.topico = topico;
        this.id = id;
        this.fechaDeCreacion = fechaCreacion;
        this.solucion = solucion;
    }




}


