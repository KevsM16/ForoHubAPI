package com.Challenge.ForoHubAPI.controller;

import com.Challenge.ForoHubAPI.domain.users.AuthenticateUserData;
import com.Challenge.ForoHubAPI.domain.users.Usuario;
import com.Challenge.ForoHubAPI.infra.security.JWTTokenDatas;
import com.Challenge.ForoHubAPI.infra.security.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")

public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;


    @PostMapping
    public ResponseEntity AuthenticateUser( @RequestBody @Valid AuthenticateUserData authenticateUserData ){
        Authentication Authenticationtoken= new UsernamePasswordAuthenticationToken(authenticateUserData.login(),authenticateUserData.clave());
        var userAutenthicated= authenticationManager.authenticate(Authenticationtoken);
        var JWTtoken= tokenService.generarToken((Usuario) userAutenthicated.getPrincipal());
        return ResponseEntity.ok(new JWTTokenDatas(JWTtoken));
    }

}
