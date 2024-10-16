package com.playtown.servicios;

import com.playtown.dominio.menuPrincipales.MenuEntrenador;
import com.playtown.repositorios.IRepositorioMenuEntrenador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioMenuEntrenador implements IServicioMenuEntrenador {

    @Autowired
    private IRepositorioMenuEntrenador iRepositorioMenuEntrenador;

    @Override
    public List<MenuEntrenador> listaMenuEntrenadores() {
        return this.iRepositorioMenuEntrenador.findAll();
    }

    @Override
    public MenuEntrenador buscarMenuEntrenadorPorId(Long id) {
        return iRepositorioMenuEntrenador.findById(id).orElse(null);
    }

    @Override
    public MenuEntrenador guardarMenuEntrenador(MenuEntrenador menuEntrenador) {
        return iRepositorioMenuEntrenador.save(menuEntrenador);
    }

    @Override
    public void eliminarMenuEntrenador(Long id) {
        iRepositorioMenuEntrenador.deleteById(id);
    }


}
