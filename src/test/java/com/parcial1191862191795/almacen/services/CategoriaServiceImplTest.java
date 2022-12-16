package com.parcial1191862191795.almacen.services;

import com.parcial1191862191795.almacen.Data.FactoryCategoriaTestData;
import com.parcial1191862191795.almacen.models.Categoria;
import com.parcial1191862191795.almacen.repository.CategoriaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class CategoriaServiceImplTest {

    @InjectMocks
    private CategoriaServiceImpl categoriaServiceImpl;

    @InjectMocks
    private Categoria categoria;

    @Mock
    private CategoriaRepository categoriaRepository;
    @Test
    void seDebeListarLasCategorias() {
        //Given
        Categoria categoria = FactoryCategoriaTestData.mockCategoria();

        //when
        when(categoriaRepository.findAll()).thenReturn(List.of(categoria));


        ResponseEntity<List<Categoria>> categoria1 = categoriaServiceImpl.allCategorias();

        //then
        Assertions.assertNotNull(categoria1);
    }
    @Test
    void whenNoEncuentraNingunaCategoria() {
        Categoria categoria = null;

        //When
        when(categoriaRepository.findAll()).thenReturn(Collections.emptyList());

        List<Categoria> categoria1 = categoriaServiceImpl.allCategorias().getBody();

        //Then
        Assertions.assertEquals(null, categoria1);

    }

    @Test
    void seDebeGuardarUnaCategoria() {
        //Given
        Categoria categoria = FactoryCategoriaTestData.mockCategoria();

        given(categoriaRepository.findById(anyLong())).willReturn(Optional.of(categoria));

        given(categoriaRepository.save(categoria)).willReturn(categoria);
        //When

        ResponseEntity<Categoria> categorySave = categoriaServiceImpl.createCategoria(categoria);

        //Then
        Assertions.assertNotNull(categorySave);
    }

}