package com.parcial1191862191795.almacen.services;

import com.parcial1191862191795.almacen.models.Articulo;
import com.parcial1191862191795.almacen.models.Categoria;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoriaService {
    ResponseEntity<Categoria> getCategoria(Long id);
    ResponseEntity<Categoria> createCategoria(Categoria categoria);
    ResponseEntity<List<Categoria>> allCategorias();
}
