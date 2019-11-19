package br.com.tt.petshop.controller;

import br.com.tt.petshop.model.Customer;
import br.com.tt.petshop.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(method = RequestMethod.GET,
            value = "/admin/customers")
    public String listar(Model model){

        model.addAttribute("customers", customerService.listar());
        return "inicial";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/admin/customers/create")
    public String createCustomer(Model model){
        Customer customer = (Customer) model.getAttribute("newCustomer");
        customerService.save(customer);
        model.addAttribute("mensagem", "New customer has been saved!");
        return "inicial";
    }

}
