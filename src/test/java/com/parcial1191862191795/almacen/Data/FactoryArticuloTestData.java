package com.parcial1191862191795.almacen.Data;

import com.parcial1191862191795.almacen.models.Articulo;

import java.util.Date;

public class FactoryArticuloTestData {
    public static Articulo mockArticulo() {
        Articulo articulo = new Articulo();
        articulo.setId(1L);
        articulo.setCodigo("A01");
        articulo.setNombre("Jabon");
        articulo.setDescripcion("Jabon azul");
        articulo.setFechaRegistro(new Date(2004, 7, 14));
        articulo.setCategoria(FactoryCategoriaTestData.mockCategoria());
        articulo.setUsuario(FactoryUsuarioTestData.mockUsuario());
        articulo.setStock(32);
        articulo.setPrecioCompra(1.200);
        articulo.setPrecioVenta(1.500);

        return articulo;
    }
    public static Articulo mockArticuloAct() {

        Articulo articulo = new Articulo();

        articulo.setId(1L);
        articulo.setCodigo("A01");
        articulo.setNombre("arroz");
        articulo.setDescripcion("otro");
        articulo.setFechaRegistro(new Date(2004, 7, 14));
        articulo.setCategoria(FactoryCategoriaTestData.mockCategoria());
        articulo.setUsuario(FactoryUsuarioTestData.mockUsuario());
        articulo.setStock(32);
        articulo.setPrecioCompra(1.200);
        articulo.setPrecioVenta(1.500);

        return articulo;
    }
}