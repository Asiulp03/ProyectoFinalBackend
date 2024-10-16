package com.playtown.servicios;

import com.playtown.dominio.menuPrincipales.MenuEntrenador;

import java.util.List;

public interface IServicioMenuEntrenador {

    List<MenuEntrenador> listaMenuEntrenadores();

    MenuEntrenador buscarMenuEntrenadorPorId(Long id);

    MenuEntrenador guardarMenuEntrenador(MenuEntrenador menuEntrenador);

    void eliminarMenuEntrenador(Long id);
}
