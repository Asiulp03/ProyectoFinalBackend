package com.playtown.repositorios;

import com.playtown.dominio.datosPersonales.Directivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositorioDirectivo extends JpaRepository<Directivo, Long> {
}
