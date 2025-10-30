package com.tienda.service;

import com.tienda.entity.Proveedor;
import java.util.List;

public interface ProveedorService {
    List<Proveedor> listar();
    Proveedor buscarPorId(Integer id);
    void guardar(Proveedor proveedor);
    void eliminar(Integer id);

    List<Proveedor> buscarPorRazonSocial(String razonSocial);
}
