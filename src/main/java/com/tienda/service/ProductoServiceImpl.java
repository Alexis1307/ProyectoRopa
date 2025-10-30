package com.tienda.service;

import com.tienda.entity.Producto;
import com.tienda.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository repo;

    public ProductoServiceImpl(ProductoRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Producto> listar() {
        return repo.findAll();
    }

    @Override
    public Producto buscarPorId(Integer id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void guardar(Producto producto) {
        repo.save(producto);
    }
    
    @Override
    public void eliminar(Integer id) {
        repo.deleteById(id);
    }

    @Override
    public List<Producto> buscarPorNombre(String nombre) {
    	return repo.findByNombreContainingIgnoreCase(nombre);
    }
}