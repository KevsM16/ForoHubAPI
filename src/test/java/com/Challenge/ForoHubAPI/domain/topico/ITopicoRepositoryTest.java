package com.Challenge.ForoHubAPI.domain.topico;

import com.Challenge.ForoHubAPI.Respuesta.RegisteryRespuestaData;
import com.Challenge.ForoHubAPI.Respuesta.Respuesta;
import jakarta.persistence.EntityManager;
import jakarta.validation.constraints.NotBlank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class ITopicoRepositoryTest {

    @Autowired
    EntityManager em;
    @Autowired
    private ITopicoRepository topicoRepository;

    @Test
    @DisplayName("Deberia devolver null cuando titulo no es el correcto o el mensaje")
    void existsByTituloAndMensajeEscenario1() {
//given o arrange
        var  topico1=registrarTopico(null,"necesito ayuda","12",null,"Necesito ayuda ","kevin","aprende a programar con java",true,null);
        //when act
        var tituloCorrecto=topicoRepository.existsByTituloAndMensaje("Hola","aaah");
        //then o assert
         assertThat(tituloCorrecto).isFalse();

    }

    public Topico registrarTopico(Long id, String titulo, String idUsuario, LocalDateTime fechaDeCreacion, String mensaje, String autor, String curso, boolean abierto, List<Respuesta> respuestas){
        var topico=new Topico(topicDatas(titulo, idUsuario, mensaje, autor, curso));
          em.persist(topico);
        return topico;
    }


    public RegisteryTopicoData topicDatas(String titulo, String idUsuario, String mensaje, String autor, String curso){
        return new RegisteryTopicoData(
                titulo,
                idUsuario,
                mensaje,
                autor,
                curso
        );
    }
    public RegisteryRespuestaData responseData( String solucion, Long topicoid){
       return new RegisteryRespuestaData(
               solucion,
               topicoid
       );
    }
}