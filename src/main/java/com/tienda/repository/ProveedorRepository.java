package com.tienda.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tienda.entity.Proveedor;

public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {
	List<Proveedor> findByRazonSocialContainingIgnoreCase(String razonSocial);
}