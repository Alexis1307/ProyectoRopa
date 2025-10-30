package com.tienda.repository;

import com.tienda.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {

    Rol findByNombre(String nombre);

    java.util.List<Rol> findByNombreContainingIgnoreCase(String nombre);
}
