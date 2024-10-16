package com.playtown.repositorios;

import com.playtown.dominio.datosPersonales.Entrenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositorioEntrenador extends JpaRepository<Entrenador, Long> {
}
