package com.playtown.controlador;

import com.playtown.dominio.login.Usuario;
import com.playtown.excepciones.ResourceNotFoundException;
import com.playtown.servicios.ServicioUsuario;
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
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:8080/")
public class ControladorIniciarSesion {

    public static final Logger logger = LoggerFactory.getLogger(ControladorIniciarSesion.class);

    @Autowired
    private ServicioUsuario servicioUsuario ;

    // Obtener lista de usuarios registrados
    @GetMapping("/usuarios")
    public List<Usuario> listaUsuario(){
        List<Usuario> listaUsuarios = this.servicioUsuario.listaUsuarios();
        logger.info("Lista de usuarios obtenidos");
        listaUsuarios.forEach(usuario -> logger.info(usuario.toString()));
        return listaUsuarios;
    }

    // Buscar usuario por ID
    @GetMapping("/usuario/{id}")
    public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable Long id) {
        Usuario usuarioEncontrado = this.servicioUsuario.buscarUsuarioPorId(id);
        if (usuarioEncontrado != null) {
            return ResponseEntity.ok(usuarioEncontrado);
        } else {
            throw new ResourceNotFoundException("No se encontro el ID");
        }
    }

    // Registrar un nuevo usuario
    @PostMapping("/registro")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario guardarUsuario(@RequestBody Usuario usuario) {
        logger.info("Usuario registrado: " + usuario);
        return this.servicioUsuario.guardarUsuario(usuario);
    }

    // Iniciar sesión (validar usuario y contraseña)
    @PostMapping("/iniciarSesion")
    public ResponseEntity<String> iniciarSesion(@RequestBody Usuario usuario) {
        boolean esValido = this.servicioUsuario.validarCredenciales(usuario.getCorreo(), usuario.getContrasena());
        if (esValido) {
            logger.info("Usuario autenticado: " + usuario.getCorreo());
            return ResponseEntity.ok("Inicio de sesión exitoso");
        } else {
            logger.warn("Intento de inicio de sesión fallido: " + usuario.getCorreo());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        }
    }

    // Actualizar los detalles de un usuario
    @PutMapping("/usuario/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(
            @PathVariable Long id,
            @RequestBody Usuario usuario) {
        Usuario usuarioEncontrado = this.servicioUsuario.buscarUsuarioPorId(id);
        if (usuarioEncontrado != null) {
            usuarioEncontrado.setNombreUsuario(usuario.getNombreUsuario());
            usuarioEncontrado.setContrasena(usuario.getContrasena());
            this.servicioUsuario.guardarUsuario(usuarioEncontrado);
            return ResponseEntity.ok(usuarioEncontrado);
        } else {
            throw new ResourceNotFoundException("Recurso no encontrado: " + id);
        }
    }

    // Eliminar un usuario
    @DeleteMapping("/usuario/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Map<String, Boolean>> eliminarUsuario(@PathVariable Long id) {
        Usuario usuario = this.servicioUsuario.buscarUsuarioPorId(id);
        if (usuario == null) {
            throw new ResourceNotFoundException("ID de usuario no encontrado: " + id);
        }
        this.servicioUsuario.eliminarUsuario(id);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }

}
