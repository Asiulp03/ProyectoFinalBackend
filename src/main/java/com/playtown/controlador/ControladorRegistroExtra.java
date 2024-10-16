package com.playtown.controlador;

import com.playtown.dominio.registros.RegistroExtra;
import com.playtown.excepciones.ResourceNotFoundException;
import com.playtown.servicios.ServicioRegistroExtra;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/registroExtra")
@CrossOrigin(origins = "http://localhost:8080/")
public class ControladorRegistroExtra {

    public static final Logger logger = LoggerFactory.getLogger(ControladorRegistroExtra.class);

    @Autowired
    private ServicioRegistroExtra servicioRegistroExtra;

    @GetMapping
    public List<RegistroExtra> obtenerRegistroExtra() {
        try {
            List<RegistroExtra> listaSegundoRegistro = this.servicioRegistroExtra.obtenerRegistroExtra();
            logger.info("Lista del segundo registro obtenidos");
            listaSegundoRegistro.forEach(registroExtra -> logger.info(registroExtra.toString()));
            return listaSegundoRegistro;
        } catch (Exception e) {
            logger.error("Error al obtener la lista de registros extra", e);
            return Collections.emptyList();
        }
    }

    // registrar un nuevo registro extra
    @PostMapping("/registroextra/nuevo")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RegistroExtra> guardarNuevoRegistroExtra(@RequestBody RegistroExtra registroExtra) {
        logger.info("guardado el segundo registro:" + registroExtra);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.servicioRegistroExtra.guardarNuevoRegistroExtra(registroExtra));
    }

    // Método para buscar un registro extra por su ID
    @GetMapping("/registroextra/{id}")
    public ResponseEntity<RegistroExtra> buscarRegistroExtraPorId(@PathVariable Long id) {
        RegistroExtra registroExtra = servicioRegistroExtra.buscarRegistroExtraPorId(id);
        if (registroExtra != null) {
            return ResponseEntity.ok(registroExtra);
        } else {
            throw  new ResourceNotFoundException("Registro extra no encontrado con ID " + id);
        }
    }

    // Método para actualizar los detalles de un registro extra
    @PutMapping("/registroextra/{id}")
    public ResponseEntity<RegistroExtra> actualizarRegistroExtra(
            @PathVariable Long id,
            @RequestBody RegistroExtra registroExtra) {
        RegistroExtra registroExtraEncontrado = this.servicioRegistroExtra.buscarRegistroExtraPorId(id);
        if (registroExtraEncontrado != null) {
            registroExtraEncontrado.setFotoPerfilPath(registroExtra.getFotoPerfilPath());
            registroExtraEncontrado.setNombreCompleto(registroExtra.getNombreCompleto());
            registroExtraEncontrado.setFechaDeNacimiento(registroExtra.getFechaDeNacimiento());
            registroExtraEncontrado.setRol(registroExtra.getRol());
            this.servicioRegistroExtra.guardarNuevoRegistroExtra(registroExtraEncontrado);
            return ResponseEntity.ok(registroExtraEncontrado);
        } else {
            throw new ResourceNotFoundException("Registro extra no encontrado con ID " + id);
        }
    }

    // Método para eliminar un registro extra por su ID
    @DeleteMapping("/registroextra/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Map<String, Boolean>> eliminarRegistroExtra(@PathVariable Long id) {
        RegistroExtra registroExtra = servicioRegistroExtra.buscarRegistroExtraPorId(id);
        if (registroExtra == null) {
            throw new ResourceNotFoundException("El Id de registro extra no encontrado " + id);
        }
        this.servicioRegistroExtra.eliminarRegistroExtra(id);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }

}
