package com.playtown.servicios;


import com.playtown.dominio.registros.Registro;


import java.util.List;

public interface IServicioRegistro {

    List<Registro> obtenerRegistro();

    Registro buscarUsuarioPorId(Long id);

    Registro guardarNuevoUsuario(Registro registro);

    void eliminarUsuario(Long id);
}
