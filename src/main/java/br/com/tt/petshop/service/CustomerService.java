package br.com.tt.petshop.service;

import br.com.tt.petshop.model.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CustomerService {
    private List<Customer> customers = new ArrayList<>();

    public List<Customer> listar(){
        return customers;
    }

    public void save(Customer customer) {
        customers.add(customer);
    }
}
