package com.playtown.controlador;


import com.playtown.dominio.registros.Registro;
import com.playtown.excepciones.ResourceNotFoundException;
import com.playtown.servicios.ServicioRegistro;
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
@RequestMapping("/api-registro")
@CrossOrigin(origins = "http://localhost:8080/")
public class ControladorRegistro {

    public static final Logger logger = LoggerFactory.getLogger(ControladorRegistro.class);

    @Autowired
    private ServicioRegistro servicioRegistro;


    // listar todos los usuarios
    @GetMapping("/registro")
    public List<Registro> obtenerRegistro() {
        List<Registro> listaRegistro = this.servicioRegistro.obtenerRegistro();
        logger.info("Registro de lista de usuarios obtenidos");
        listaRegistro.forEach(registro -> logger.info(registro.toString()));
        return listaRegistro;
    }

    // registrar un nuevo usuario
    @PostMapping("/usuarios/nuevo")
    @ResponseStatus(HttpStatus.CREATED)
    public Registro registrarUsuario(@RequestBody Registro registro) {
       logger.info("Registro de usuario agregado: " + registro);
        return this.servicioRegistro.guardarNuevoUsuario(registro);
    }

    // Método para buscar un usuario por su ID
    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Registro> buscarUsuarioPorId(@PathVariable Long id) {
        Registro registroEncontrado = servicioRegistro.buscarUsuarioPorId(id);
        if (registroEncontrado != null) {
            return ResponseEntity.ok(registroEncontrado);
        } else {
           throw new ResourceNotFoundException("No se encontro el ID ");
        }
    }

    // Método para actualizar los detalles de un usuario
    @PutMapping("/usuarios/{id}")
    public ResponseEntity<Registro> actualizarRegistroUsuario(
            @PathVariable Long id,
            @RequestBody Registro registro) {
        Registro usuarioEncontrado = this.servicioRegistro.buscarUsuarioPorId(id);
        if (usuarioEncontrado != null) {
            usuarioEncontrado.setNombreUsuario(registro.getNombreUsuario());
            usuarioEncontrado.setCorreoElectronico(registro.getCorreoElectronico());
            usuarioEncontrado.setContrasena(registro.getContrasena());
            this.servicioRegistro.guardarNuevoUsuario(usuarioEncontrado);
            return ResponseEntity.ok(usuarioEncontrado);
        } else {
            throw new ResourceNotFoundException("Recurso no encontrado: " + id);
        }
    }

    // Método para eliminar un registro usuario por su ID
    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarRegistroUsuario(@PathVariable Long id) {
        Registro usuario = servicioRegistro.buscarUsuarioPorId(id);
        if (usuario == null) {
            throw new ResourceNotFoundException("El Id de usuario de registro no encontrado: " + id);
        }
        this.servicioRegistro.eliminarUsuario(id);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }


}
