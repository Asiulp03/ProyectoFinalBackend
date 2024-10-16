package com.playtown.servicios;

import com.playtown.dominio.menuPrincipales.MenuDirectivo;
import com.playtown.repositorios.IRepositorioMenuDirectivo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioMenuDirectivo implements IServicioMenuDirectivo{

    @Autowired
    private IRepositorioMenuDirectivo iRepositorioMenuDirectivo;

    @Override
    public List<MenuDirectivo> listaMenuDirectivos() {
        return iRepositorioMenuDirectivo.findAll();
    }

    @Override
    public MenuDirectivo buscarMenuDirectivoPorId(Long id) {
        return iRepositorioMenuDirectivo.findById(id).orElse(null);
    }

    @Override
    public MenuDirectivo guardarMenuDirectivo(MenuDirectivo menuDirectivo) {
        return iRepositorioMenuDirectivo.save(menuDirectivo);
    }

    @Override
    public void eliminarMenuDirectivo(Long id) {
        iRepositorioMenuDirectivo.deleteById(id);
    }
}
