package com.playtown.servicios;

import com.playtown.dominio.menuPrincipales.MenuDirectivo;

import java.util.List;

public interface IServicioMenuDirectivo {

    List<MenuDirectivo> listaMenuDirectivos();

    MenuDirectivo buscarMenuDirectivoPorId(Long id);

    MenuDirectivo guardarMenuDirectivo(MenuDirectivo menuDirectivo);

    void eliminarMenuDirectivo(Long id);
}
