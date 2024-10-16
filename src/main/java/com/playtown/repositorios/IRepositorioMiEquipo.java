package com.playtown.repositorios;

import com.playtown.dominio.menuPrincipales.MiEquipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositorioMiEquipo extends JpaRepository<MiEquipo, Long> {
}
