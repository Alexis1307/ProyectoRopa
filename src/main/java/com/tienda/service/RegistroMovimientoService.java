package com.tienda.service;

import com.tienda.entity.RegistroMovimiento;
import java.util.List;

public interface RegistroMovimientoService {
    void registrarMovimiento(String usuario, String accion, String entidad, String detalle);
    List<RegistroMovimiento> listar();
}
