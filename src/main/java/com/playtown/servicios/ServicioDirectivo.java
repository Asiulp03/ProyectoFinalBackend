package com.playtown.servicios;

import com.playtown.dominio.datosPersonales.Directivo;
import com.playtown.repositorios.IRepositorioDirectivo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioDirectivo implements  IServicioDirectivo{

    @Autowired
    private IRepositorioDirectivo iRepositorioDirectivo;


    @Override
    public List<Directivo> listaDirectivos() {
        return this.iRepositorioDirectivo.findAll();
    }

    @Override
    public Directivo buscarDirectivoPorId(Long id) {
        return this.iRepositorioDirectivo.findById(id).orElse(null);
    }

    @Override
    public Directivo guardarDirectivo(Directivo directivo) {
        return iRepositorioDirectivo.save(directivo);
    }

    @Override
    public void eliminarDirectivo(Long id) {
        this.iRepositorioDirectivo.deleteById(id);
    }
}
