package com.parcial1191862191795.almacen.repository;

import com.parcial1191862191795.almacen.models.Articulo;
import com.parcial1191862191795.almacen.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
