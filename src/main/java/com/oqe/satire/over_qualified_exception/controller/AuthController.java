package com.oqe.satire.over_qualified_exception.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String showAuthPage(Model model) {
        model.addAttribute("title", "Integración de Identidad Cuántica");
        return "auth";
    }
}
