package com.oqe.satire.over_qualified_exception.controller;

import com.oqe.satire.over_qualified_exception.model.Customer;
import com.oqe.satire.over_qualified_exception.service.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public String showAuthPage(Model model, HttpSession session) {
        if (session.getAttribute("customer") != null) {
            return "redirect:/"; // Si ya está logueado, al home
        }
        model.addAttribute("title", "Integración de Identidad Cuántica");
        return "auth";
    }

    @PostMapping("/login")
    public String performLogin(@RequestParam String nickname,
            @RequestParam String password,
            HttpSession session,
            Model model) {
        Optional<Customer> customer = authService.login(nickname, password);
        if (customer.isPresent()) {
            session.setAttribute("customer", customer.get());
            return "redirect:/";
        } else {
            model.addAttribute("loginError",
                    "Error: Identidad criptográfica no rastreada. Revisa tu nivel de miseria.");
            model.addAttribute("activeTab", "login");
            model.addAttribute("title", "Integración de Identidad Cuántica");
            return "auth";
        }
    }

    @PostMapping("/register")
    public String performRegister(@RequestParam String nickname,
            @RequestParam String password,
            HttpSession session,
            Model model) {
        boolean registered = authService.register(nickname, password);
        if (registered) {
            // Loguear auto al registrar
            Customer c = authService.login(nickname, password).orElseThrow();
            session.setAttribute("customer", c);
            return "redirect:/";
        } else {
            model.addAttribute("registerError",
                    "Error: Ese Nickname ya fue anexado a la blockchain de esclavitud.");
            model.addAttribute("activeTab", "register");
            model.addAttribute("title", "Integración de Identidad Cuántica");
            return "auth";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
