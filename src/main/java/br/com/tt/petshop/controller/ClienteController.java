package br.com.tt.petshop.controller;

import br.com.tt.petshop.model.Cliente;
import br.com.tt.petshop.service.ClienteService;
import br.com.tt.petshop.exceptions.NegocioException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @RequestMapping(method = RequestMethod.GET,
            value = "/admin/clientes")
    public String listar(Model model){
        model.addAttribute("mensagem",
                "Bem vindo a lista de clientes da petshop");
        model.addAttribute("clientes", clienteService.listar(null, null));
        return "cliente/clientes";
    }

    @RequestMapping(method = RequestMethod.GET,
            value = "/admin/clientes/criar")
    public String clienteCriar(Model model){
        model.addAttribute("novoCliente", new Cliente());
        return "cliente/cliente_criar";
    }

    @RequestMapping(method = RequestMethod.POST,
            value = "/admin/clientes/criarNovo")
    public String criarNovo(Cliente cliente, Model model) {

        try {
            clienteService.salvar(cliente);
            model.addAttribute("mensagem",
                    "Cliente salvo com sucesso!");
        } catch (NegocioException e) {
            model.addAttribute("mensagem",
                    "Erro: ".concat(e.getMessage()));
        }
        model.addAttribute("clientes",
                clienteService.listar(null, null));

        return "cliente/clientes";
    }


}