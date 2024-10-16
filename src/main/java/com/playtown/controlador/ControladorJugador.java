package com.playtown.controlador;

import com.playtown.dominio.datosPersonales.Jugador;
import com.playtown.excepciones.ResourceNotFoundException;
import com.playtown.servicios.ServicioJugador;
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
@RequestMapping("/Jugador")
@CrossOrigin(origins = "http://localhost:8080/")
public class ControladorJugador {

    public static final Logger logger= LoggerFactory.getLogger(ControladorJugador.class);

    @Autowired
    private ServicioJugador servicioJugador;

    @GetMapping("/Jugador")
    public List<Jugador> obtenerListaJugadores(){
        List<Jugador> listaJugadores = this.servicioJugador.obtenerListaJugadores();
        logger.info("lista de dotos personales de los jugadores");
        listaJugadores.forEach(datosJugador -> logger.info(datosJugador.toString()));
        return listaJugadores;
    }

    @GetMapping("/Jugador/{id}")
    public ResponseEntity<Jugador> buscarDatosJugadorPorId(@PathVariable Long id) {
        Jugador EncontradoJugador = this.servicioJugador.buscarDatosJugadorPorId(id);
        if (EncontradoJugador != null) {
            return ResponseEntity.ok(EncontradoJugador);
        } else {
            throw new ResourceNotFoundException("No se encontro el ID");
        }
    }

    @PostMapping("/Jugador/nuevo")
    @ResponseStatus(HttpStatus.CREATED)
    public Jugador crearJugador(@RequestBody Jugador datosJugador) {
        logger.info("Los datos del jugador agregado:  " + datosJugador);
        return this.servicioJugador.guardarNuevoJugador(datosJugador);
    }

    @PutMapping("/Jugador/{id}")
    public ResponseEntity<Jugador> actualizarJugador(
            @PathVariable Long id,
            @RequestBody Jugador datosJugador) {
        Jugador jugadorEncontrado = this.servicioJugador.buscarDatosJugadorPorId(id);
        if (jugadorEncontrado != null) {
            jugadorEncontrado.setNombreJugador(datosJugador.getNombreJugador());
            jugadorEncontrado.setFechaDeNacimiento(datosJugador.getFechaDeNacimiento());
            jugadorEncontrado.setCorreoElectronico(datosJugador.getCorreoElectronico());
            jugadorEncontrado.setClub(datosJugador.getClub());
            jugadorEncontrado.setPorsiciones(datosJugador.getPorsiciones());
            this.servicioJugador.guardarNuevoJugador(jugadorEncontrado);
            return ResponseEntity.ok(jugadorEncontrado);
        } else {
            throw new ResourceNotFoundException("Recurso no encontrado: " + id);
        }
    }

    @DeleteMapping("/Jugador/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity <Map<String, Boolean>> eliminarDatosJugador(@PathVariable Long id) {
        Jugador datosJugador = this.servicioJugador.buscarDatosJugadorPorId(id);
        if (datosJugador == null){
            throw new ResourceNotFoundException("Id no encontrado:" + id);
        }
        this.servicioJugador.eliminarJugador(id);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Eliminado datos del jugador", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}




