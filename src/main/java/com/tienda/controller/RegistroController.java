package com.tienda.controller;

import com.tienda.service.RegistroMovimientoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegistroController {

    private final RegistroMovimientoService registroService;

    public RegistroController(RegistroMovimientoService registroService) {
        this.registroService = registroService;
    }

    @GetMapping("/registro")
    public String mostrarRegistro(Model model) {
        model.addAttribute("movimientos", registroService.listar());
        return "registro";
    }
}
