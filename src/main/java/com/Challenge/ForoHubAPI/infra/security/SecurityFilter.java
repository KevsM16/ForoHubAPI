package com.Challenge.ForoHubAPI.infra.security;

import com.Challenge.ForoHubAPI.domain.users.IUsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.xml.crypto.dsig.spec.XPathType;
import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("El filtro esta siendo enviado");
        //obteniendo token desde el header
        var authHeader=request.getHeader("Authorization");//replaceAll("Bearer ","");
              /* if(token==""||token==null){
                    throw new RuntimeException("el token enviado no es valido");
                }*/
                if(authHeader!=null){
                  var  token=authHeader.substring(7);
                    System.out.println(authHeader);
                    System.out.println(tokenService.getSubject(token));//este usuario tiene sesion?
                    var subject=tokenService.getSubject(token);//subject es el nombre de usuario
                    if(subject!=null){
                        //token valido
                      var usuario=  usuarioRepository.findByLogin(subject);
                      var authentication=new UsernamePasswordAuthenticationToken(usuario,null,
                              usuario.getAuthorities());//forzando inicio de sesion
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
        filterChain.doFilter(request,response);

    }
}
