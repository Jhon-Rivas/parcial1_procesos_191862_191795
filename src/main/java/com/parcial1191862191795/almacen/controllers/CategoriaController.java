package com.parcial1191862191795.almacen.controllers;

import com.parcial1191862191795.almacen.models.Categoria;
import com.parcial1191862191795.almacen.repository.CategoriaRepository;
import com.parcial1191862191795.almacen.services.CategoriaService;
import com.parcial1191862191795.almacen.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(maxAge = 3600)
@RestController
public class CategoriaController{

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private JWTUtil jwtUtil;


    @GetMapping(value = "/categoria/{id_ct}")
    public ResponseEntity getCategoria(@PathVariable Long id_ct, @RequestHeader(value = "Authorization") String token) {
        try{
            if(jwtUtil.getKey(token) == null){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
            }
            return categoriaService.getCategoria(id_ct);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
        }
    }

    @PostMapping("/categoria")
    public ResponseEntity crearCategoria (@RequestBody Categoria categoria, @RequestHeader(value = "Authorization") String token) {
        try{
            if(jwtUtil.getKey(token) == null){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
            }
            return categoriaService.createCategoria(categoria);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
        }
    }

    @GetMapping("/categorias/listado")
    public ResponseEntity listarCategorias(@RequestHeader(value = "Authorization") String token) {
        if(jwtUtil.getKey(token) == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
        }
        return categoriaService.allCategorias();
    }
}
