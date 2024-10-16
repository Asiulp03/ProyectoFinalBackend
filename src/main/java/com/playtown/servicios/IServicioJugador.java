package com.playtown.servicios;

import com.playtown.dominio.datosPersonales.Jugador;

import java.util.List;

public interface IServicioJugador {

    List<Jugador> obtenerListaJugadores();

    Jugador buscarDatosJugadorPorId(Long id);

    Jugador guardarNuevoJugador(Jugador datosJugador);

    void eliminarJugador(Long id);
}
