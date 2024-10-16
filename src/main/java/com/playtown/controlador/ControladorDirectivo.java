package com.playtown.controlador;

import com.playtown.dominio.datosPersonales.Directivo;
import com.playtown.excepciones.ResourceNotFoundException;
import com.playtown.servicios.ServicioDirectivo;
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
@RequestMapping("/directivo")
@CrossOrigin(origins = "http://localhost:8080/")
public class ControladorDirectivo {

    public  static  final Logger logger = LoggerFactory.getLogger(ControladorDirectivo.class);

    @Autowired
    private ServicioDirectivo servicioDirectivo;

    @GetMapping("/directivo")
    public List<Directivo> listaDirectivo(){
        List<Directivo> listaDirectivos = this.servicioDirectivo.listaDirectivos();
        logger.info("Lista de directivos obtenidos");
        listaDirectivos.forEach(directivo ->  logger.info(directivo.toString()));
        return listaDirectivos;
    }

    @GetMapping("/directivo/{id}")
    public ResponseEntity<Directivo> buscarDirectivoPorId(@PathVariable Long id){
        Directivo directivoEncontrado = this.servicioDirectivo.buscarDirectivoPorId(id);
        if(directivoEncontrado != null){
            return ResponseEntity.ok(directivoEncontrado);
        }else{
            throw new ResourceNotFoundException("No se encontró el ID");
        }
    }

    @PostMapping("/directivo")
    @ResponseStatus(HttpStatus.CREATED)
    public Directivo guardarDirectivo(@RequestBody Directivo directivo){
        logger.info("Directivo agregado: " + directivo);
        return this.servicioDirectivo.guardarDirectivo(directivo);
    }

    @PutMapping("/directivo/{id}")
    public ResponseEntity<Directivo> actualizarDirectivo (
            @PathVariable Long id,
            @RequestBody Directivo directivo){
        Directivo directivoEncontrado = this.servicioDirectivo.buscarDirectivoPorId(id);
        if(directivoEncontrado != null){
            directivoEncontrado.setId(id);
            directivoEncontrado.setNombreDirectivo(directivo.getNombreDirectivo());
            directivoEncontrado.setFechaDeNacimiento(directivo.getFechaDeNacimiento());
            directivoEncontrado.setCorreoElectronico(directivo.getCorreoElectronico());
            directivoEncontrado.setTelefono(directivo.getTelefono());
            this.servicioDirectivo.guardarDirectivo(directivoEncontrado);
            return ResponseEntity.ok(directivoEncontrado);
        }else{
            throw new ResourceNotFoundException("Recurso no encontrado:" +id);
        }
    }

    @DeleteMapping("/directivo/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Map<String, Boolean>> eliminarDirectivo(@PathVariable Long id){
        Directivo directivo = this.servicioDirectivo.buscarDirectivoPorId(id);
        if(directivo == null){
            throw new ResourceNotFoundException("Id no encontrado: "+id);
        }
        this.servicioDirectivo.eliminarDirectivo(id);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }


}
