package com.playtown.servicios;

import com.playtown.dominio.datosPersonales.Jugador;
import com.playtown.repositorios.IRepositorioJugador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioJugador implements IServicioJugador {

    @Autowired
    private IRepositorioJugador iRepositorioJugador;

    @Override
    public List<Jugador> obtenerListaJugadores(){
        return this.iRepositorioJugador.findAll();
    }

    @Override
    public Jugador buscarDatosJugadorPorId(Long id) {
        return this.iRepositorioJugador.findById(id).orElse(null);
    }

    @Override
    public Jugador guardarNuevoJugador(Jugador datosJugador){
        return this.iRepositorioJugador.save(datosJugador);
    }

    @Override
    public void eliminarJugador(Long id){
        this.iRepositorioJugador.deleteById(id);
    }

}
