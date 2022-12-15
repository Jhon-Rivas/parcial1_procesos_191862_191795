package com.parcial1191862191795.almacen.services;

import com.parcial1191862191795.almacen.models.Articulo;
import com.parcial1191862191795.almacen.repository.ArticuloRepository;
import com.parcial1191862191795.almacen.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticuloServiceImpl implements ArticuloService {
    @Autowired
    private ArticuloRepository articuloRepository;

    @Autowired
    private JWTUtil jwtUtil;

    @Override
    public ResponseEntity<Articulo> getArticuloByCodigo(String codigo) {
        Optional<Articulo> articulos= articuloRepository.findByCodigo(codigo);
        if (articulos.isPresent()){
            return new ResponseEntity(articulos, HttpStatus.OK);
        }
        return  ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Articulo> createArticulo(Articulo articulo) {
        try {
            articuloRepository.save(articulo);
            return new ResponseEntity(articulo,HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();

        }
    }

    @Override
    public ResponseEntity<List<Articulo>> allArticulos() {
        List<Articulo> articulos= articuloRepository.findAll();
        if(articulos.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(articulos,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Articulo> editArticulo(String codigo, Articulo articulo) {
        Optional<Articulo> articuloBD = articuloRepository.findByCodigo(codigo);
        if (articuloBD.isPresent()) {
            try {
                articuloBD.get().setCodigo(articulo.getCodigo());
                articuloBD.get().setNombre(articulo.getNombre());
                articuloBD.get().setDescripcion(articulo.getDescripcion());
                articuloBD.get().setFechaRegistro(articulo.getFechaRegistro());
                articuloBD.get().setUsuario(articulo.getUsuario());
                articuloBD.get().setCategoria(articulo.getCategoria());
                articuloBD.get().setStock(articulo.getStock());
                articuloBD.get().setPrecioVenta(articulo.getPrecioVenta());
                articuloBD.get().setPrecioCompra(articulo.getPrecioCompra());
                articuloRepository.save(articuloBD.get());
                return new ResponseEntity(articuloBD, HttpStatus.OK);

            } catch (Exception e) {
                return ResponseEntity.badRequest().build();

            }
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Articulo> deleteArticuloBycodigo(String codigo) {
        Optional<Articulo> articuloBD = articuloRepository.findByCodigo(codigo);
        if(articuloBD.isPresent()){
            articuloRepository.delete(articuloBD.get());
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
