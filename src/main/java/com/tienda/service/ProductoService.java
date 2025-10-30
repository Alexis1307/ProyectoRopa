package com.tienda.service;

import com.tienda.entity.Producto;
import java.util.List;

public interface ProductoService {
    
	List<Producto> listar();
    Producto buscarPorId(Integer id);
    void guardar(Producto producto);
    void eliminar(Integer id);

    List<Producto> buscarPorNombre(String nombre);
}
