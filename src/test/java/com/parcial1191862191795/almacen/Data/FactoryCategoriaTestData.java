package com.parcial1191862191795.almacen.Data;

import com.parcial1191862191795.almacen.models.Categoria;

public class FactoryCategoriaTestData {

    public static Categoria mockCategoria() {

        Categoria categoria = new Categoria();
        categoria.setId_ct(1l);
        categoria.setNombre("Aseo");
        categoria.setDescripcion("articulos para el aseo");

        return categoria;
    }

}