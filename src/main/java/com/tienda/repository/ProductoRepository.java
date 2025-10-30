package com.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tienda.entity.Producto;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    List<Producto> findByNombreContainingIgnoreCase(String nombre);

}