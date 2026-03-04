package com.oqe.satire.over_qualified_exception.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContractController {

    @GetMapping("/contract")
    public String showContract(Model model) {
        return "contract";
    }
}
