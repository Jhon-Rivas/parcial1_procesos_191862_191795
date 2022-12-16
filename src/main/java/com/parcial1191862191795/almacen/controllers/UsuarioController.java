package com.parcial1191862191795.almacen.controllers;

import com.parcial1191862191795.almacen.models.Usuario;
import com.parcial1191862191795.almacen.services.UsuarioService;
import com.parcial1191862191795.almacen.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(maxAge = 3600)
@RestController
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private JWTUtil jwtUtil;


    @GetMapping(value = "/usuario/ver/{id}")
    public ResponseEntity getUsuario(@PathVariable Long id, @RequestHeader(value = "Authorization") String token) {
        try {
            if (jwtUtil.getKey(token) == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
            }
            return usuarioService.getUserById(id);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido. "+e.getMessage());
        }

    }

    @GetMapping(value = "/usuario{correo}")
    public ResponseEntity getUsuarioByCorreo(@PathVariable String correo,
                                             @RequestHeader(value = "Authorization") String token ){
        try {
            if (jwtUtil.getKey(token) == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
            }
            return usuarioService.getUsuarioByCorreo(correo);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido. "+e.getMessage());
        }

    }

    @PostMapping("/usuario")
    public ResponseEntity crearUsuario(@Valid @RequestBody Usuario usuario) {
        return usuarioService.createUser(usuario);
    }

    @GetMapping("/usuarios")
    public ResponseEntity listarUsuarios(@RequestHeader(value = "Authorization") String token) {
        try {
            if (jwtUtil.getKey(token) == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
            }
            return usuarioService.allUsers();
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido. "+e.getMessage());
        }
    }

    @GetMapping("/usuario/{nombre}/{apellidos}")
    public ResponseEntity listaPorNombreApellidos(@PathVariable String nombre, @PathVariable String apellidos, @RequestHeader(value = "Authorization") String token) {
        try {
            if (jwtUtil.getKey(token) == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
            }
            return usuarioService.allUsersByNameAndLastName(nombre, apellidos);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido. "+e.getMessage());
        }
    }

    @GetMapping("/usuario/nombre/{nombre}")
    public ResponseEntity listaPorNombre(@PathVariable String nombre, @RequestHeader(value = "Authorization") String token) {
        try {
            if (jwtUtil.getKey(token) == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
            }
            return usuarioService.allUsersByName(nombre);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido. "+e.getMessage());
        }
    }

    @GetMapping("/usuario/apellidos/{apellidos}")
    public ResponseEntity listaPorApellidos(@PathVariable String apellidos, @RequestHeader(value = "Authorization") String token) {
        try {
            if (jwtUtil.getKey(token) == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
            }
            return usuarioService.allUsersByLastName(apellidos);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido. "+e.getMessage());
        }
    }

    @PutMapping("/usuario/modificar/{id}")
    public ResponseEntity editarUsuario(@PathVariable Long id, @Valid @RequestBody  Usuario usuario, @RequestHeader(value = "Authorization") String token) {
        try {
            if (jwtUtil.getKey(token) == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
            }
            return usuarioService.editUser(id, usuario);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido. "+e.getMessage());
        }
    }

    @DeleteMapping("/usuario/{id}")
    public ResponseEntity eliminarUsuario(@PathVariable Long id, @RequestHeader(value = "Authorization") String token) {
        try {
            if (jwtUtil.getKey(token) == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
            }
            return usuarioService.deleteUserById(id);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido. "+e.getMessage());
        }
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
