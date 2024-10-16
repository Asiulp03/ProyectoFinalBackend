package com.playtown.servicios;

import com.playtown.dominio.menuPrincipales.MenuJugador;

import java.util.List;

public interface IServicioMenuJugador {

    List<MenuJugador> listaMenuJugadores();

    MenuJugador buscarMenuJugadorPorId(Long id);

    MenuJugador guardarMenuJugador(MenuJugador menuJugador);

    void eliminarMenuJugador(Long id);
}
