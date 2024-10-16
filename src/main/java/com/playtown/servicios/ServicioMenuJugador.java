package com.playtown.servicios;

import com.playtown.dominio.menuPrincipales.MenuJugador;
import com.playtown.repositorios.IRepositorioMenuJugador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioMenuJugador implements IServicioMenuJugador{

    @Autowired
    private IRepositorioMenuJugador iRepositorioMenuJugador;

    @Override
    public List<MenuJugador> listaMenuJugadores() {
        return this.iRepositorioMenuJugador.findAll();
    }

    @Override
    public MenuJugador buscarMenuJugadorPorId(Long id) {
        return this.iRepositorioMenuJugador.findById(id).orElse(null);
    }

    @Override
    public MenuJugador guardarMenuJugador(MenuJugador menuJugador) {
        return this.iRepositorioMenuJugador.save(menuJugador);
    }

    @Override
    public void eliminarMenuJugador(Long id) {
        this.iRepositorioMenuJugador.deleteById(id);
    }

}
