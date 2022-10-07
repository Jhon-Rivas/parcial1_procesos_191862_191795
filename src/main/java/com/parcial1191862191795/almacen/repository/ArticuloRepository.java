package com.parcial1191862191795.almacen.repository;

import com.parcial1191862191795.almacen.models.Articulo;
import com.parcial1191862191795.almacen.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArticuloRepository extends JpaRepository<Articulo, Long> {

    Optional<Articulo> findByCodigo(String codigo);
}
