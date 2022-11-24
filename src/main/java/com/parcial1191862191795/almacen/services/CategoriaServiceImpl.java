package com.parcial1191862191795.almacen.services;

import com.parcial1191862191795.almacen.models.Categoria;
import com.parcial1191862191795.almacen.repository.CategoriaRepository;
import com.parcial1191862191795.almacen.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private JWTUtil jwtUtil;

    @Override
    public ResponseEntity<Categoria> getCategoria(Long id) {
        Optional<Categoria> categoria= categoriaRepository.findById(id);
        if(categoria.isPresent()){
            return new ResponseEntity(categoria, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Categoria> createCategoria(Categoria categoria) {
        try{
            categoriaRepository.save(categoria);
            return new ResponseEntity(categoria, HttpStatus.CREATED);
        }catch (Exception e){
            System.out.println(e.fillInStackTrace());
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<List<Categoria>> allCategorias() {
        List<Categoria> categorias = categoriaRepository.findAll();
        if (categorias.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(categorias,HttpStatus.OK);
    }
}
