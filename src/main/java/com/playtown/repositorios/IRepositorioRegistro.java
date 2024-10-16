package com.playtown.repositorios;

import com.playtown.dominio.registros.Registro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositorioRegistro extends JpaRepository<Registro, Long> {

}
