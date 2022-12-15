package com.parcial1191862191795.almacen.controllers;

import com.parcial1191862191795.almacen.models.Articulo;
import com.parcial1191862191795.almacen.services.ArticuloService;
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
public class ArticuloController {
    @Autowired
    private ArticuloService articuloService;
    @Autowired
    private JWTUtil jwtUtil;

    @GetMapping(value = "/articulo/{codigo}")
    public ResponseEntity getArticulo(@PathVariable String codigo, @RequestHeader(value = "Authorization") String token) {
        try{
            if(jwtUtil.getKey(token) == null){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
            }
            return articuloService.getArticuloByCodigo(codigo);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
        }

    }

    @PostMapping("/articulo")
    public ResponseEntity crearArticulo (@Valid @RequestBody Articulo articulo, @RequestHeader(value = "Authorization") String token) {
        try{
            if(jwtUtil.getKey(token) == null){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
            }
            return articuloService.createArticulo(articulo);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
        }
    }

    @GetMapping("/articulos")
    public ResponseEntity listarArticulos(@RequestHeader(value = "Authorization") String token) {
        if(jwtUtil.getKey(token) == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
        }
        return articuloService.allArticulos();
    }

    @PutMapping("/articulo/{codigo}")
    public ResponseEntity editarArticulo(@PathVariable String codigo, @Valid @RequestBody  Articulo articulo, @RequestHeader(value = "Authorization") String token) {
        if(jwtUtil.getKey(token) == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
        }
        return articuloService.editArticulo(codigo, articulo);
    }

    @DeleteMapping("/articulo/{codigo}")
    public ResponseEntity eliminarArticulo(@PathVariable String codigo, @RequestHeader(value = "Authorization") String token) {
        if(jwtUtil.getKey(token) == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
        }
        return articuloService.deleteArticuloBycodigo(codigo);
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
