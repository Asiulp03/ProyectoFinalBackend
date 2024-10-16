package com.playtown.repositorios;

import com.playtown.dominio.registros.RegistroExtra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositorioRegistroExtra extends JpaRepository<RegistroExtra, Long> {
}
