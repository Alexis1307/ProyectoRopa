package com.tienda.controller;

import com.tienda.entity.Producto;
import com.tienda.entity.Proveedor;
import com.tienda.service.ProveedorService;
import com.tienda.service.RegistroMovimientoService;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/proveedor")
public class ProveedorController {

    private final ProveedorService proveedorService;

    @Autowired
    private RegistroMovimientoService registroMovimientoService;

    public ProveedorController(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    private String obtenerUsuarioActual() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (auth != null) ? auth.getName() : "Desconocido";
    }

    @GetMapping("/")
    public String inicio() {
        return "redirect:/proveedor/lista";
    }

    @GetMapping("/lista")
    public String listarProveedores(Model model) {
        model.addAttribute("listaProveedores", proveedorService.listar());
        return "consultaProveedor";
    }

    @GetMapping("/editar/{id}")
    public String editarProveedor(@PathVariable("id") Integer id, Model model) {
        Proveedor prov = proveedorService.buscarPorId(id);
        model.addAttribute("proveedor", prov);
        return "mantenimientoProveedor";
    }

    @PostMapping("/actualizar")
    public String actualizarProveedor(@ModelAttribute Proveedor proveedor) {
        proveedorService.guardar(proveedor);

        String usuario = obtenerUsuarioActual();
        String detalle = "Proveedor actualizado: " + proveedor.getRazonSocial();
        registroMovimientoService.registrarMovimiento(usuario, "Editar", "Proveedor", detalle);

        return "redirect:/proveedor/lista";
    }

    @GetMapping("/nuevo")
    public String nuevoProveedor(Model model) {
        model.addAttribute("proveedor", new Proveedor());
        return "añadirProveedor";
    }

    
    @PostMapping("/guardar")
    public String guardarProveedor(
    		@ModelAttribute Proveedor proveedor,
    		@RequestParam("accion") String accion) {
        proveedorService.guardar(proveedor);

        String usuario = obtenerUsuarioActual();
        String detalle = "Proveedor: " + proveedor.getRazonSocial();
        
        registroMovimientoService.registrarMovimiento(usuario, accion, "Proveedor", detalle);

        return "redirect:/proveedor/lista";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProveedor(@PathVariable("id") Integer id) {
        proveedorService.eliminar(id);

        String usuario = obtenerUsuarioActual();
        String detalle = "Proveedor ID: " + id;
        registroMovimientoService.registrarMovimiento(usuario, "Eliminar", "Proveedor", detalle);

        return "redirect:/proveedor/lista";
    }

    @GetMapping("/buscar")
    public String buscarPorRazonSocial(@RequestParam("razonSocial") String razonSocial, Model model) {
        List<Proveedor> resultados = proveedorService.buscarPorRazonSocial(razonSocial);
        model.addAttribute("listaProveedores", resultados);
        model.addAttribute("nombreBuscado", razonSocial);
        return "buscarProveedor";
    }

}
