package com.tienda.controller;

import com.tienda.entity.Producto;
import com.tienda.service.ProductoService;
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
@RequestMapping("/producto")
public class ProductoController {

    private final ProductoService productoService;
    private final ProveedorService proveedorService;

    @Autowired
    private RegistroMovimientoService registroMovimientoService;

    public ProductoController(ProductoService productoService, ProveedorService proveedorService) {
        this.productoService = productoService;
        this.proveedorService = proveedorService;
    }

    private String obtenerUsuarioActual() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (auth != null) ? auth.getName() : "Desconocido";
    }

    @GetMapping("/")
    public String inicio() {
        return "redirect:/producto/lista";
    }

    @GetMapping("/lista")
    public String listarProductos(Model model) {
        model.addAttribute("listaProductos", productoService.listar());
        return "consultaProducto";
    }

    @GetMapping("/editar/{id}")
    public String editarProducto(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("producto", productoService.buscarPorId(id));
        model.addAttribute("proveedores", proveedorService.listar());
        return "mantenimientoProducto";
    }

    @PostMapping("/actualizar")
    public String actualizarProducto(@ModelAttribute Producto producto) {
        productoService.guardar(producto);

        String usuario = obtenerUsuarioActual();
        String detalle = "Producto actualizado: " + producto.getNombre();
        registroMovimientoService.registrarMovimiento(usuario, "Editar", "Producto", detalle);

        return "redirect:/producto/lista";
    }

    @GetMapping("/nuevo")
    public String nuevoProducto(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("proveedores", proveedorService.listar());
        return "añadirProducto";
    }

    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute Producto producto) {
        productoService.guardar(producto);

        String usuario = obtenerUsuarioActual();
        String accion = (producto.getIdProd() == null) ? "Añadir" : "Editar";
        String detalle = "Producto: " + producto.getNombre();
        registroMovimientoService.registrarMovimiento(usuario, accion, "Producto", detalle);

        return "redirect:/producto/lista";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable("id") Integer id) {
        productoService.eliminar(id);

        String usuario = obtenerUsuarioActual();
        String detalle = "Producto ID: " + id;
        registroMovimientoService.registrarMovimiento(usuario, "Eliminar", "Producto", detalle);

        return "redirect:/producto/lista";
    }

    @GetMapping("/buscar")
    public String buscarPorNombre(@RequestParam("nombre") String nombre, Model model) {
        List<Producto> resultados = productoService.buscarPorNombre(nombre);
        model.addAttribute("listaProductos", resultados);
        model.addAttribute("nombreBuscado", nombre);
        return "buscarProducto";
    }

}
