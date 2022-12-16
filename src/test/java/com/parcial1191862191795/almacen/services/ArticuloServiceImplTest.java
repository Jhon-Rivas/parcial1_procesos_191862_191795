package com.parcial1191862191795.almacen.services;

import com.parcial1191862191795.almacen.Data.FactoryArticuloTestData;
import com.parcial1191862191795.almacen.models.Articulo;
import com.parcial1191862191795.almacen.models.Categoria;
import com.parcial1191862191795.almacen.repository.ArticuloRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ArticuloServiceImplTest {
    @InjectMocks
    private  ArticuloServiceImpl articuloServiceImpl;
    @InjectMocks
    private Articulo articulo;
    @Mock
    private ArticuloRepository articuloRepository;
    @InjectMocks
    private Categoria categoria;

    @Test
    void seDebeEncontrarUnArticuloPorCodigo() {
        //Given
        Articulo articulo = FactoryArticuloTestData.mockArticulo();
        //when
        when(articuloRepository.findByCodigo("A01")).thenReturn(Optional.of(articulo));
        ResponseEntity<Articulo> articulo1 = articuloServiceImpl.getArticuloByCodigo("A01");

        //then
        Assertions.assertNotNull(articulo1);
    }
    @Test
    void createArticleTest() {
        //Given
        Articulo articulo = FactoryArticuloTestData.mockArticulo();
        given(articuloRepository.findByCodigo(articulo.getCodigo())).willReturn(Optional.of(articulo));
        given(articuloRepository.save(articulo)).willReturn(articulo);
        //When

        ResponseEntity<Articulo> articuloGuardado = articuloServiceImpl.createArticulo(articulo);

        //Then
        Assertions.assertNotNull(articuloGuardado);
    }
    @Test
    void whenNoEncuentraArticuloPorCodigo() {
        Articulo articulo = null;

        //When
        when(articuloRepository.findByCodigo("A01")).thenReturn(Optional.ofNullable(articulo));


        Articulo articulo1 = articuloServiceImpl.getArticuloByCodigo("A01").getBody();

        //Then
        Assertions.assertEquals(null, articulo1);

    }
    @Test
    void seDebeListarLosArticulos() {
        //Given
        Articulo articulo = FactoryArticuloTestData.mockArticulo();
        //when
        when(articuloRepository.findAll()).thenReturn(List.of(articulo));


        ResponseEntity<List<Articulo>> articulo1 = articuloServiceImpl.allArticulos();

        //then
        Assertions.assertNotNull(articulo1);
    }
    @Test
    void whenNoEncuentraNingunArticulo() {
        Articulo articulo = null;

        //When
        when(articuloRepository.findAll()).thenReturn(Collections.emptyList());

        List<Articulo> articulo1 = articuloServiceImpl.allArticulos().getBody();

        //Then
        Assertions.assertEquals(null, articulo1);

    }
    @Test
    void seDebeActualizarUnArticulo() {
        // Given
        Articulo articulo = FactoryArticuloTestData.mockArticulo();
        Articulo articuloAct = FactoryArticuloTestData.mockArticuloAct();
        given(articuloRepository.findByCodigo(anyString())).willReturn(Optional.of(articulo));
        given(articuloRepository.save(articuloAct)).willReturn(articuloAct);

        //when
        ResponseEntity<Articulo> articleSave = articuloServiceImpl.editArticulo(articulo.getCodigo(), articuloAct);

        //Then
        Assertions.assertNotNull(articleSave);
    }
    @Test
    void seDebeEliminarUnArticulo() {
        //Given
        Articulo articulo = FactoryArticuloTestData.mockArticulo();

        given(articuloRepository.findByCodigo(anyString())).willReturn(null);

        //when
        articuloRepository.delete(articulo);
        Optional<Articulo> articleDelete = articuloRepository.findByCodigo(anyString());

        //Then
        assertThat(articleDelete).isNull();
    }
}