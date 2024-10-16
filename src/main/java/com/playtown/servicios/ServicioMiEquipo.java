package com.playtown.servicios;

import com.playtown.dominio.menuPrincipales.MiEquipo;
import com.playtown.repositorios.IRepositorioMiEquipo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ServicioMiEquipo implements IServicioMiEquipo{

    @Autowired
    private IRepositorioMiEquipo iRepositorioMiEquipo;


    @Override
    public List<MiEquipo> listaMiEquipos(){
        return this.iRepositorioMiEquipo.findAll();
    }

    @Override
    public MiEquipo buscarMiEquipoPorId(Long id) {
        return this.iRepositorioMiEquipo.findById(id).orElse(null);
    }

    @Override
    public MiEquipo guardarMiEquipo(MiEquipo miEquipo) {
        return this.iRepositorioMiEquipo.save(miEquipo);
    }

    @Override
    public void eliminarMiEquipo(Long id) {
        this.iRepositorioMiEquipo.deleteById(id);
    }

}
