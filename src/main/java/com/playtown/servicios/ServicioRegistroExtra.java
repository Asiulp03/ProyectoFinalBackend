package com.playtown.servicios;

import com.playtown.dominio.registros.RegistroExtra;
import com.playtown.repositorios.IRepositorioRegistroExtra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioRegistroExtra implements  IServicioRegistroExtra{

    @Autowired
    private IRepositorioRegistroExtra iRepositorioRegistroExtra;

    @Override
    public List<RegistroExtra> obtenerRegistroExtra(){
        return this.iRepositorioRegistroExtra.findAll();
    }

    @Override
    public RegistroExtra buscarRegistroExtraPorId(Long id) {
        return this.iRepositorioRegistroExtra.findById(id).orElse(null);
    }

    @Override
    public RegistroExtra guardarNuevoRegistroExtra(RegistroExtra registroExtra) {
        return this.iRepositorioRegistroExtra.save(registroExtra);
    }

    @Override
    public void eliminarRegistroExtra(Long id) {
        this.iRepositorioRegistroExtra.deleteById(id);
    }

}
