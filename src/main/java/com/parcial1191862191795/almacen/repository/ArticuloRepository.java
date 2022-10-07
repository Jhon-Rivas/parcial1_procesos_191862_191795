package com.parcial1191862191795.almacen.repository;

import com.parcial1191862191795.almacen.models.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticuloRepository extends JpaRepository<Articulo, Long> {

    List<Articulo> findAllByCodigo(String codigo);

}
