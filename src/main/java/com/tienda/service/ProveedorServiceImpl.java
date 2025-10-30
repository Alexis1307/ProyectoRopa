package com.tienda.service;

import com.tienda.entity.Proveedor;
import com.tienda.repository.ProveedorRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProveedorServiceImpl implements ProveedorService {

    private final ProveedorRepository repo;

    public ProveedorServiceImpl(ProveedorRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Proveedor> listar() {
        return repo.findAll();
    }
    
    @Override
    public Proveedor buscarPorId(Integer id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void guardar(Proveedor proveedor) {
        repo.save(proveedor);
    }
    
    @Override
    public void eliminar(Integer id) {
        repo.deleteById(id);
    }
    
    @Override
    public List<Proveedor> buscarPorRazonSocial(String razonSocial) {
		return repo.findByRazonSocialContainingIgnoreCase(razonSocial);
    }

}
