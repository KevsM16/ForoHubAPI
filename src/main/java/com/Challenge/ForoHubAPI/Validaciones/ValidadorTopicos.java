package com.Challenge.ForoHubAPI.Validaciones;

import com.Challenge.ForoHubAPI.domain.topico.RegisteryTopicoData;
import com.Challenge.ForoHubAPI.domain.topico.UpdatePosts;

public interface ValidadorTopicos<T> {
      public void validacion(T data);
}
