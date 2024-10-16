package com.playtown.servicios;

import com.playtown.dominio.menuPrincipales.MiEquipo;

import java.util.List;

public interface IServicioMiEquipo {

    List<MiEquipo> listaMiEquipos();

    MiEquipo buscarMiEquipoPorId(Long id);

    MiEquipo guardarMiEquipo(MiEquipo miEquipo);

    void eliminarMiEquipo(Long id);
}
