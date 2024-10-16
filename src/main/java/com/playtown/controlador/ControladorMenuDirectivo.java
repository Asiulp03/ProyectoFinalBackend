package com.playtown.controlador;

import com.playtown.dominio.menuPrincipales.MenuDirectivo;
import com.playtown.excepciones.ResourceNotFoundException;
import com.playtown.servicios.ServicioMenuDirectivo;
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
@RequestMapping("/menuDirectivo")
@CrossOrigin(origins = "http://localhost:8080/")
public class ControladorMenuDirectivo {

    public static final Logger logger = LoggerFactory.getLogger(ControladorDirectivo.class);

    @Autowired
    private ServicioMenuDirectivo servicioMenuDirectivo;


    @GetMapping("/menuDirectivo")
    public List<MenuDirectivo> listaMenuDirectivo(){
        List<MenuDirectivo> listaMenuDirectivos = this.servicioMenuDirectivo.listaMenuDirectivos();
        logger.info("Lista de menu directivos obtenidos");
        listaMenuDirectivos.forEach(menuDirectivo ->  logger.info(menuDirectivo.toString()));
        return listaMenuDirectivos;
    }

    @GetMapping("/menuDirectivo/{id}")
    public ResponseEntity<MenuDirectivo> buscarMenuDirectivoPorId(@PathVariable Long id){
        MenuDirectivo menuDirectivoEncontrado = this.servicioMenuDirectivo.buscarMenuDirectivoPorId(id);
        if(menuDirectivoEncontrado != null){
            return ResponseEntity.ok(menuDirectivoEncontrado);
        }else{
            throw new ResourceNotFoundException("No se encontró el ID");
        }
    }

    @PostMapping("/menuDirectivo")
    @ResponseStatus(HttpStatus.CREATED)
    public MenuDirectivo guardarMenuDirectivo(@RequestBody MenuDirectivo menuDirectivo){
        logger.info("Menu directivo agregado: " + menuDirectivo);
        return this.servicioMenuDirectivo.guardarMenuDirectivo(menuDirectivo);
    }

    @PutMapping("/menuDirectivo/{id}")
    public ResponseEntity<MenuDirectivo> actualizarMenuDirectivo (
            @PathVariable Long id,
            @RequestBody MenuDirectivo menuDirectivo){
        MenuDirectivo menuDirectivoEncontrado = this.servicioMenuDirectivo.buscarMenuDirectivoPorId(id);
        if(menuDirectivoEncontrado != null){
            menuDirectivoEncontrado.setFotoPerfilDirectivo(menuDirectivo.getFotoPerfilDirectivo());
            menuDirectivoEncontrado.setNombreDelDirectivo(menuDirectivo.getNombreDelDirectivo());
            menuDirectivoEncontrado.setEdad(menuDirectivo.getEdad());
            menuDirectivoEncontrado.setTorneos(menuDirectivo.getTorneos());
            this.servicioMenuDirectivo.guardarMenuDirectivo(menuDirectivoEncontrado);
            return ResponseEntity.ok(menuDirectivoEncontrado);

        }else{
            throw new ResourceNotFoundException("Recurso no encontrado:" +id);
        }
    }

    @DeleteMapping("/menuDirectivo/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Map<String, Boolean>> eliminarMenuDirectivo(@PathVariable Long id){
        MenuDirectivo menuDirectivo = this.servicioMenuDirectivo.buscarMenuDirectivoPorId(id);
        if(menuDirectivo == null){
            throw new ResourceNotFoundException("Id no encontrado: "+id);
        }
        this.servicioMenuDirectivo.eliminarMenuDirectivo(id);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }



}
