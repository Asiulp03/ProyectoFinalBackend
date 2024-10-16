package com.playtown.controlador;

import com.playtown.dominio.menuPrincipales.MenuJugador;
import com.playtown.excepciones.ResourceNotFoundException;
import com.playtown.servicios.ServicioMenuJugador;
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
@RequestMapping("/menuJugador")
@CrossOrigin(origins = "http://localhost:8080")
public class ControladorMenuJugador {

    public static final Logger logger = LoggerFactory.getLogger(ControladorMenuJugador.class);

    @Autowired
    private ServicioMenuJugador servicioMenuJugador;

    @GetMapping("/menuJugador")
    public List<MenuJugador> listaMenuJugador(){
        List<MenuJugador> listaMenuJugadores = this.servicioMenuJugador.listaMenuJugadores();
        logger.info("Lista del menu de los jugadores obtenidos");
        listaMenuJugadores.forEach(menuJugador ->  logger.info(menuJugador.toString()));
        return listaMenuJugadores;
    }

    @GetMapping("/menuJugador/{id}")
    public ResponseEntity<MenuJugador> buscarMenuJugadorPorId(@PathVariable Long id){
        MenuJugador menuJugadorEncontrado = this.servicioMenuJugador.buscarMenuJugadorPorId(id);
        if(menuJugadorEncontrado != null){
            return ResponseEntity.ok(menuJugadorEncontrado);
        }else{
            throw new ResourceNotFoundException("No se encontró el ID");
        }
    }

    @PostMapping("/menuJugador")
    @ResponseStatus(HttpStatus.CREATED)
    public MenuJugador guardarMenuJugador(@RequestBody MenuJugador menuJugador){
        logger.info("MenuJugador agregado: " + menuJugador);
        return this.servicioMenuJugador.guardarMenuJugador(menuJugador);
    }

    @PutMapping("/menuJugador/{id}")
    public ResponseEntity<MenuJugador> actualizarMenuJugador (
            @PathVariable Long id,
            @RequestBody MenuJugador menuJugador){
        MenuJugador menuJugadorEncontrado = this.servicioMenuJugador.buscarMenuJugadorPorId(id);
        if(menuJugadorEncontrado != null){
            menuJugadorEncontrado.setFotoPerfilJuagador(menuJugador.getFotoPerfilJuagador());
            menuJugadorEncontrado.setNombreDelJugador(menuJugador.getNombreDelJugador());
            menuJugadorEncontrado.setEdad(menuJugador.getEdad());
            menuJugadorEncontrado.setGoles(menuJugador.getGoles());
            menuJugadorEncontrado.setAsistensias(menuJugador.getAsistensias());
            this.servicioMenuJugador.guardarMenuJugador(menuJugadorEncontrado);
            return ResponseEntity.ok(menuJugadorEncontrado);

        }else{
            throw new ResourceNotFoundException("Recurso no encontrado:" +id);
        }
    }

    @DeleteMapping("/menuJugador/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Map<String, Boolean>> eliminarMenuJugador(@PathVariable Long id){
        MenuJugador menuJugador = this.servicioMenuJugador.buscarMenuJugadorPorId(id);
        if(menuJugador == null){
            throw new ResourceNotFoundException("Id no encontrado: "+id);
        }
        this.servicioMenuJugador.eliminarMenuJugador(id);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
