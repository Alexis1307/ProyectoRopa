package com.tienda.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "registro_movimiento")
public class RegistroMovimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String usuario;       
    private String accion;         
    private String entidad;        
    private String detalle;       
    private LocalDateTime fechaHora; 

    public RegistroMovimiento() {}

    public RegistroMovimiento(String usuario, String accion, String entidad, String detalle) {
        this.usuario = usuario;
        this.accion = accion;
        this.entidad = entidad;
        this.detalle = detalle;
        this.fechaHora = LocalDateTime.now();
    }

    public Integer getId() { return id; }
    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getAccion() { return accion; }
    public void setAccion(String accion) { this.accion = accion; }

    public String getEntidad() { return entidad; }
    public void setEntidad(String entidad) { this.entidad = entidad; }

    public String getDetalle() { return detalle; }
    public void setDetalle(String detalle) { this.detalle = detalle; }

    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }
}
