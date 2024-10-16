package com.playtown.repositorios;

import com.playtown.dominio.datosPersonales.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositorioJugador extends JpaRepository<Jugador, Long> {

}
