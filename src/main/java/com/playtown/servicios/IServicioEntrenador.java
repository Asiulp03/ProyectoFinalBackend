package com.playtown.servicios;

import com.playtown.dominio.datosPersonales.Entrenador;

import java.util.List;

public interface IServicioEntrenador {

    List<Entrenador> listaEntrenadores();

    Entrenador buscarEntrenadorPorId(Long id);

    Entrenador guardarEntrenador(Entrenador entrenador);

    void eliminarEntrenador(Long id);
}
