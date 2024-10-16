package com.playtown.servicios;

import com.playtown.dominio.login.Usuario;

import java.util.List;

public interface IServicioUsuario {

    List<Usuario> listaUsuarios();

    Usuario buscarUsuarioPorId(Long id);

    Usuario guardarUsuario(Usuario usuario);

    boolean validarCredenciales(String correo, String contrasena);

    void eliminarUsuario(Long id);
}
