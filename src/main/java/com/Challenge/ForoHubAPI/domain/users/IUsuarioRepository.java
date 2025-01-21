package com.Challenge.ForoHubAPI.domain.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUsuarioRepository extends JpaRepository<Usuario,Long> {
    UserDetails findByLogin(String username);
}
