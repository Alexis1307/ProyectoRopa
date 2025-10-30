package com.tienda.controller;

import com.tienda.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TiendaController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/inicio")
    public String inicioTienda(Model model) {
        model.addAttribute("listaProductos", productoService.listar());
        return "/inicio";
    }
}
