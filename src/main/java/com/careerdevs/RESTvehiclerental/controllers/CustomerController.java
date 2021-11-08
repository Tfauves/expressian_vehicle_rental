package com.careerdevs.RESTvehiclerental.controllers;


import com.careerdevs.RESTvehiclerental.models.Customer;
import com.careerdevs.RESTvehiclerental.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository repository;

    @GetMapping
    public @ResponseBody List<Customer> getCustomers() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public @ResponseBody Customer getOneById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer newCustomer) {
        return new ResponseEntity<>(repository.save(newCustomer), HttpStatus.CREATED) ;
    }

    @PutMapping("/{id}")
    public @ResponseBody Customer updateCustomer(@PathVariable long id, @RequestBody Customer updateData) {
        Customer cust = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (updateData.getFirstName() != null) cust.setFirstName(updateData.getFirstName());
        if (updateData.getLastName() != null) cust.setLastName(updateData.getLastName());
        if (updateData.getEmail() != null) cust.setEmail(updateData.getEmail());

        return repository.save(cust);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer (@PathVariable Long id) {
         repository.deleteById(id);
    }
}
