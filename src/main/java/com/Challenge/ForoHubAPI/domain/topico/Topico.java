package com.Challenge.ForoHubAPI.domain.topico;


import com.Challenge.ForoHubAPI.Respuesta.Respuesta;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name= "topicos")
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

@Column(name = "usuario_id")
    private String idUsuario;

    private String titulo;

    private String mensaje;

    @Column(name ="fecha_de_creacion")
    private LocalDateTime fechaDeCreacion;

    private   String autor;

    private  String curso;

    @OneToMany(mappedBy = "topico" , cascade=CascadeType.ALL)
    private List<Respuesta> respuestas;

 private boolean abierto;

    public boolean isAbierto() {
        return abierto;
    }

    public Long getId() {
        return id;
    }



    public String getIdUsuario() {
        return idUsuario;
    }

    public List<Respuesta> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<Respuesta> respuestas) {
       respuestas.forEach(respuesta -> respuesta.setTopico(this));
        this.respuestas = respuestas;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getAutor() {
        return autor;
    }




  @PrePersist
  public void asignarValoresPredeterminados(){
        if(fechaDeCreacion==null){
            this.fechaDeCreacion=LocalDateTime.now();
        }
        if (!abierto){
            this.abierto=true;
        }
  }

    public LocalDateTime getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public String getCurso() {
        return curso;
    }

    public Topico(RegisteryTopicoData registeryTopicoData) {
        this.titulo= registeryTopicoData.titulo();
        this.idUsuario= registeryTopicoData.idUsuario();
        this.mensaje= registeryTopicoData.mensaje();
        this.autor= registeryTopicoData.autor();
        this.curso= registeryTopicoData.curso();

    }

    public Topico(Long id, String titulo, String idUsuario, LocalDateTime fechaDeCreacion, String mensaje, String autor, String curso, boolean abierto, List<Respuesta>respuestas ) {
        this.id = id;
        this.titulo = titulo;
        this.idUsuario=idUsuario;
        this.fechaDeCreacion = fechaDeCreacion;
        this.mensaje = mensaje;
        this.autor = autor;
        this.curso = curso;
        this.abierto=true;
        this.respuestas=respuestas;
    }

    public  void actualizarTopicos(UpdatePosts updatePosts){

        if(this.titulo!=null){
            this.titulo= updatePosts.titulo();
        }

        if(this.mensaje!=null){
            this.mensaje= updatePosts.mensaje();
        }
        if(this.autor!=null){
            this.autor= updatePosts.autor();
        }
        if(this.curso!=null){
            this.curso=updatePosts.curso();
        }


    }


}
