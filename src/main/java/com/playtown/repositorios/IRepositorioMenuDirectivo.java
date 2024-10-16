package com.playtown.repositorios;

import com.playtown.dominio.menuPrincipales.MenuDirectivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositorioMenuDirectivo extends JpaRepository<MenuDirectivo, Long> {
}
