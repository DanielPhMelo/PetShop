package br.com.tt.petshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping
public class InicialController {

    @RequestMapping(method = RequestMethod.GET,
            value = "/admin/inicial")
    public String iniciar(Model model){
        model.addAttribute("mensagem",
                "Bem vindo ao Sistema Petshop");
        return "inicial";
    }
}
