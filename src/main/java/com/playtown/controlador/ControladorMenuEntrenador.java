package com.playtown.controlador;

import com.playtown.dominio.menuPrincipales.MenuEntrenador;
import com.playtown.excepciones.ResourceNotFoundException;
import com.playtown.servicios.ServicioMenuEntrenador;
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
@RequestMapping("/menuEntrenador")
@CrossOrigin(origins = "http://localhost:8080/")
public class ControladorMenuEntrenador {

    public static final Logger logger = LoggerFactory.getLogger(ControladorMenuEntrenador.class);

    @Autowired
    private ServicioMenuEntrenador servicioMenuEntrenador;

    @GetMapping("/menuEntrenador")
    public List<MenuEntrenador> listaMenuEntrenador(){
        List<MenuEntrenador> listaMenuEntrenadores = this.servicioMenuEntrenador.listaMenuEntrenadores();
        logger.info("Lista de menu de entrenadores obtenidos");
        listaMenuEntrenadores.forEach(menuEntrenador -> logger.info(menuEntrenador.toString()));
        return listaMenuEntrenadores;
    }

    @GetMapping("/menuEntrenador/{id}")
    public ResponseEntity<MenuEntrenador> buscarMenuEntrenadorPorId(@PathVariable Long id){
        MenuEntrenador menuEntrenadorEncontrado = this.servicioMenuEntrenador.buscarMenuEntrenadorPorId(id);
        if(menuEntrenadorEncontrado != null){
            return ResponseEntity.ok(menuEntrenadorEncontrado);
        }else{
            throw new ResourceNotFoundException("No se encontró el ID");
        }
    }

    @PostMapping("/menuEntrenador")
    @ResponseStatus(HttpStatus.CREATED)
    public MenuEntrenador guardarMenuEntrenador(@RequestBody MenuEntrenador menuEntrenador){
        logger.info("Menu de entrenador agregado: " + menuEntrenador);
        return this.servicioMenuEntrenador.guardarMenuEntrenador(menuEntrenador);
    }

    @PutMapping("/menuEntrenador/{id}")
    public ResponseEntity<MenuEntrenador> actualizarMenuEntrenador (
            @PathVariable Long id,
            @RequestBody MenuEntrenador menuEntrenador){
        MenuEntrenador menuEntrenadorEncontrado = this.servicioMenuEntrenador.buscarMenuEntrenadorPorId(id);
        if(menuEntrenadorEncontrado != null){
            menuEntrenadorEncontrado.setFotoPerfilEntrenador(menuEntrenador.getFotoPerfilEntrenador());
            menuEntrenadorEncontrado.setNombreDelEntrenador(menuEntrenador.getNombreDelEntrenador());
            menuEntrenadorEncontrado.setEdad(menuEntrenador.getEdad());
            menuEntrenadorEncontrado.setPartidos(menuEntrenador.getPartidos());
            this.servicioMenuEntrenador.guardarMenuEntrenador(menuEntrenadorEncontrado);
            return ResponseEntity.ok(menuEntrenadorEncontrado);
        }else{
            throw new ResourceNotFoundException("Recurso no encontrado:" +id);
        }
    }

    @DeleteMapping("/menuEntrenador/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Map<String, Boolean>> eliminarMenuEntrenador(@PathVariable Long id){
        MenuEntrenador menuEntrenador = this.servicioMenuEntrenador.buscarMenuEntrenadorPorId(id);
        if(menuEntrenador == null){
            throw new ResourceNotFoundException("Id no encontrado: "+id);
        }
        this.servicioMenuEntrenador.eliminarMenuEntrenador(id);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
