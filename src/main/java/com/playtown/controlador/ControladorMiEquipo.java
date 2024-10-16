package com.playtown.controlador;

import com.playtown.dominio.menuPrincipales.MiEquipo;
import com.playtown.excepciones.ResourceNotFoundException;
import com.playtown.servicios.ServicioMiEquipo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api-MiEquipo")
@CrossOrigin(origins = "http://localhost:8080/")
public class ControladorMiEquipo {

    public static final Logger logger = LoggerFactory.getLogger(ControladorMiEquipo.class);

    @Autowired
    private ServicioMiEquipo servicioMiEquipo;

    @GetMapping("/miEquipo")
    public List<MiEquipo> listaMiEquipo(){
        List<MiEquipo> listaMiEquipos = this.servicioMiEquipo.listaMiEquipos();
        logger.info("Lista de Mi Equipos obtenidos");
        listaMiEquipos.forEach(miEquipo -> logger.info(miEquipo.toString()));
        return listaMiEquipos;
    }

    @GetMapping("/miEquipo/{id}")
    public ResponseEntity<MiEquipo> buscarMiEquipoPorId(@PathVariable Long id){
        MiEquipo miEquipoEncontrado = this.servicioMiEquipo.buscarMiEquipoPorId(id);
        if(miEquipoEncontrado != null){
            return ResponseEntity.ok(miEquipoEncontrado);
        }else{
            throw new ResourceNotFoundException("No se encontró el ID");
        }
    }

    @PostMapping("/miEquipo")
    @ResponseStatus(HttpStatus.CREATED)
    public MiEquipo guardarMiEquipo(@RequestBody MiEquipo miEquipo){
        logger.info("Mi Equipo agregado: " + miEquipo);
        return this.servicioMiEquipo.guardarMiEquipo(miEquipo);
    }

    @PutMapping("/miEquipo/{id}")
    public ResponseEntity<MiEquipo> actualizarMiEquipo (
            @PathVariable Long id,
            @RequestBody MiEquipo miEquipo){
        MiEquipo miEquipoEncontrado = this.servicioMiEquipo.buscarMiEquipoPorId(id);
        if(miEquipoEncontrado != null){
            miEquipoEncontrado.setNombreDelEquipo(miEquipo.getNombreDelEquipo());
            miEquipoEncontrado.setPartidosJugados(miEquipo.getPartidosJugados());
            miEquipoEncontrado.setPartidosGanados(miEquipo.getPartidosGanados());
            miEquipoEncontrado.setPartidosPerdidos(miEquipo.getPartidosPerdidos());
            miEquipoEncontrado.setPartidosEmpatados(miEquipo.getPartidosEmpatados());
            miEquipoEncontrado.setGolesAFavor(miEquipo.getGolesAFavor());
            miEquipoEncontrado.setGolesEnContra(miEquipo.getGolesEnContra());
            this.servicioMiEquipo.guardarMiEquipo(miEquipoEncontrado);
            return ResponseEntity.ok(miEquipoEncontrado);
        }else{
            throw new ResourceNotFoundException("Recurso no encontrado:" +id);
        }
    }

    @DeleteMapping("/miEquipo/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Map<String, Boolean>> eliminarMiEquipo(@PathVariable Long id){
        MiEquipo miEquipo = this.servicioMiEquipo.buscarMiEquipoPorId(id);
        if(miEquipo == null){
            throw new ResourceNotFoundException("Id no encontrado: "+id);
        }
        this.servicioMiEquipo.eliminarMiEquipo(id);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
