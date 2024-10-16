package com.playtown.repositorios;

import com.playtown.dominio.menuPrincipales.MenuJugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositorioMenuJugador extends JpaRepository<MenuJugador, Long> {
}
