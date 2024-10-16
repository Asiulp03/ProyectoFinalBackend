package com.playtown.servicios;

import com.playtown.dominio.registros.Registro;
import com.playtown.repositorios.IRepositorioRegistro;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioRegistro implements IServicioRegistro {

    @Autowired
    private IRepositorioRegistro iRepositorioRegistro;

    @Override
    public List<Registro> obtenerRegistro(){
        return this.iRepositorioRegistro.findAll();
    }

    @Override
    public Registro buscarUsuarioPorId(Long id) {
        return this.iRepositorioRegistro.findById(id).orElse(null);
    }

    @Override
    public Registro guardarNuevoUsuario(Registro registro) {
        return this.iRepositorioRegistro.save(registro);
    }

    @Override
    public  void eliminarUsuario(Long id){
        this.iRepositorioRegistro.deleteById(id);
    }
}
