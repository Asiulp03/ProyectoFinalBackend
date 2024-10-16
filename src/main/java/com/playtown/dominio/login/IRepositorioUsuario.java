package com.playtown.dominio.login;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositorioUsuario extends JpaRepository<Usuario, Long> {
    Usuario findByCorreo(String correo);

}
