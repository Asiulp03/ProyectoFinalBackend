package com.playtown.servicios;

import com.playtown.dominio.login.Usuario;
import com.playtown.dominio.login.IRepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioUsuario implements IServicioUsuario {

    @Autowired
    private IRepositorioUsuario iRepositorioUsuario;

    @Override
    public List<Usuario> listaUsuarios() {
        return this.iRepositorioUsuario.findAll();
    }

    @Override
    public Usuario buscarUsuarioPorId(Long id){
        return this.iRepositorioUsuario.findById(id).orElse(null);
    }

    @Override
    public Usuario guardarUsuario (Usuario usuario){
        return  this.iRepositorioUsuario.save(usuario);
    }

    @Override
    public boolean validarCredenciales(String correo, String contrasena) {
        Usuario usuario = iRepositorioUsuario.findByCorreo(correo);
        return usuario != null && usuario.getContrasena().equals(contrasena);
    }

    @Override
    public  void eliminarUsuario(Long id){
        this.iRepositorioUsuario.deleteById(id);
    };

}
