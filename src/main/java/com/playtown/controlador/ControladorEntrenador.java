package com.playtown.controlador;


import com.playtown.dominio.datosPersonales.Entrenador;
import com.playtown.excepciones.ResourceNotFoundException;
import com.playtown.servicios.ServicioEntrenador;
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
@RequestMapping("/entrenador")
@CrossOrigin(origins = "http://localhost:8080/")
public class ControladorEntrenador {

    public static final Logger logger = LoggerFactory.getLogger(ControladorEntrenador.class);

    @Autowired
    private ServicioEntrenador servicioEntrenador;

    @GetMapping("/entrenador")
    public List<Entrenador> listaEntrenador(){
        List<Entrenador> listaEntrenadores = this.servicioEntrenador.listaEntrenadores();
        logger.info("Lista de entrenadores obtenidos");
        listaEntrenadores.forEach(entrenador ->  logger.info(entrenador.toString()));
        return listaEntrenadores;
    }

    @GetMapping("/entrenador/{id}")
    public ResponseEntity<Entrenador> buscarEntrenadorPorId(@PathVariable Long id){
        Entrenador entrenadorEncontrado = this.servicioEntrenador.buscarEntrenadorPorId(id);
        if(entrenadorEncontrado != null){
            return ResponseEntity.ok(entrenadorEncontrado);
        }else{
            throw new ResourceNotFoundException("No se encontró el ID");
        }
    }

    @PostMapping("/entrenador")
    @ResponseStatus(HttpStatus.CREATED)
    public Entrenador guardarEntrenador(@RequestBody Entrenador entrenador){
        logger.info("Entrenador agregado: " + entrenador);
        return this.servicioEntrenador.guardarEntrenador(entrenador);
    }

    @PutMapping("/entrenador/{id}")
    public ResponseEntity<Entrenador> actualizarEntrenador (
            @PathVariable Long id,
            @RequestBody Entrenador entrenador){
        Entrenador entrenadorEncontrado = this.servicioEntrenador.buscarEntrenadorPorId(id);
        if(entrenadorEncontrado != null){
            entrenadorEncontrado.setNombreEntrenador(entrenador.getNombreEntrenador());
            entrenadorEncontrado.setFechaDeNacimiento(entrenador.getFechaDeNacimiento());
            entrenadorEncontrado.setCorreoElectronico(entrenador.getCorreoElectronico());
            entrenadorEncontrado.setTelefono(entrenador.getTelefono());
            this.servicioEntrenador.guardarEntrenador(entrenadorEncontrado);
            return ResponseEntity.ok(entrenadorEncontrado);
        }else{
            throw new ResourceNotFoundException("Recurso no encontrado:" +id);
        }
    }

    @DeleteMapping("/entrenador/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Map<String, Boolean>> eliminarEntrenador(@PathVariable Long id){
        Entrenador entrenador = this.servicioEntrenador.buscarEntrenadorPorId(id);
        if(entrenador == null){
            throw new ResourceNotFoundException("Id no encontrado: "+id);
        }
        this.servicioEntrenador.eliminarEntrenador(id);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }






}
