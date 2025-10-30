package com.tienda.service;

import com.tienda.entity.RegistroMovimiento;
import com.tienda.repository.RegistroMovimientoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RegistroMovimientoServiceImpl implements RegistroMovimientoService {

    private final RegistroMovimientoRepository repo;

    public RegistroMovimientoServiceImpl(RegistroMovimientoRepository repo) {
        this.repo = repo;
    }

    @Override
    public void registrarMovimiento(String usuario, String accion, String entidad, String detalle) {
        RegistroMovimiento registro = new RegistroMovimiento(usuario, accion, entidad, detalle);
        repo.save(registro);
    }

    @Override
    public List<RegistroMovimiento> listar() {
        return repo.findAll();
    }
}
