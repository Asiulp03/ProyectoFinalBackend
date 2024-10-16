package com.playtown.repositorios;

import com.playtown.dominio.menuPrincipales.MenuEntrenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositorioMenuEntrenador extends JpaRepository<MenuEntrenador, Long> {
}
