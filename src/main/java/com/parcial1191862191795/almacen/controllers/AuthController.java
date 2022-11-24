package com.parcial1191862191795.almacen.controllers;

import com.parcial1191862191795.almacen.models.Articulo;
import com.parcial1191862191795.almacen.models.Usuario;
import com.parcial1191862191795.almacen.services.ArticuloService;
import com.parcial1191862191795.almacen.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping(value = "auth/login")

    public ResponseEntity login(@RequestBody Usuario usuario){
        return usuarioService.login(usuario.getCorreo(), usuario.getPassword());

    }
}
