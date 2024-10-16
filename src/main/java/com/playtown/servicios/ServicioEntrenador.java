package com.playtown.servicios;

import com.playtown.dominio.datosPersonales.Entrenador;
import com.playtown.repositorios.IRepositorioEntrenador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioEntrenador implements IServicioEntrenador{

    @Autowired
    private IRepositorioEntrenador iRepositorioEntrenador;

    @Override
    public List<Entrenador> listaEntrenadores() {
        return  this.iRepositorioEntrenador.findAll();
    }

    @Override
    public Entrenador buscarEntrenadorPorId(Long id) {
        return this.iRepositorioEntrenador.findById(id).orElse(null);
    }

    @Override
    public Entrenador guardarEntrenador(Entrenador entrenador) {
        return this.iRepositorioEntrenador.save(entrenador);
    }

    @Override
    public void eliminarEntrenador(Long id) {
        this.iRepositorioEntrenador.deleteById(id);
    }
}
