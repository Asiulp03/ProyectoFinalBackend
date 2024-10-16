package com.playtown.servicios;

import com.playtown.dominio.datosPersonales.Directivo;

import java.util.List;

public interface IServicioDirectivo {

    List<Directivo> listaDirectivos();

    Directivo buscarDirectivoPorId(Long id);

    Directivo guardarDirectivo(Directivo directivo);

    void eliminarDirectivo(Long id);
}
