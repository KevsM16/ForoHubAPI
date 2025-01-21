package com.Challenge.ForoHubAPI.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration//al escanearse primero corren los objectos con anotacion configuration
@EnableWebSecurity//indica que el metodo se escribe para sobreescribir el comportamiento del metodo
public class SecurityConfigurations {

    @Autowired
    private SecurityFilter securityFilter;
@Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       return http.csrf(h->h.disable())//el csrf evita suplantacion de identidad
               .sessionManagement(sm ->sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))//quiero que mi poliza de creacion sea stateless
               .authorizeHttpRequests(req->{
                 req.requestMatchers(HttpMethod.POST,"login").permitAll()
                         .requestMatchers("/v3/api-docs/**","/swagger-ui.html","/swagger-ui/**").permitAll();
                 req.anyRequest().authenticated();
               })
               .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)//validar si el usuario que inicia sesion existe y si esta autenticado
               .build();
  }


  @Bean
  public PasswordEncoder PasswordEncoder(){
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }
}
