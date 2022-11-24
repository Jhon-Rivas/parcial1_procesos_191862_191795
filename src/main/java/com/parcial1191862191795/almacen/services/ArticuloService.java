package com.parcial1191862191795.almacen.services;

import com.parcial1191862191795.almacen.models.Articulo;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ArticuloService {
    ResponseEntity<Articulo> getArticuloByCodigo(String codigo);
    ResponseEntity<Articulo> createArticulo(Articulo articulo);
    ResponseEntity<List<Articulo>> allArticulos();
    ResponseEntity<Articulo> editArticulo(String codigo, Articulo articulo);
    ResponseEntity<Articulo> deleteArticuloBycodigo(String codigo);
}
