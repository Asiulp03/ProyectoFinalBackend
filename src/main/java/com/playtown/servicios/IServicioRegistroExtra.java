package com.playtown.servicios;

import com.playtown.dominio.registros.RegistroExtra;

import java.util.List;

public interface IServicioRegistroExtra {

    List<RegistroExtra> obtenerRegistroExtra();

    RegistroExtra buscarRegistroExtraPorId(Long id);

    RegistroExtra guardarNuevoRegistroExtra(RegistroExtra registroExtra);

    void eliminarRegistroExtra(Long id);


}
