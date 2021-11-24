package com.careerdevs.RESTvehiclerental.controllers;


import com.careerdevs.RESTvehiclerental.models.customer.Customer;
import com.careerdevs.RESTvehiclerental.models.vehicle.Car;
import com.careerdevs.RESTvehiclerental.repositories.CarRepository;
import com.careerdevs.RESTvehiclerental.repositories.CustomerRepository;
import com.careerdevs.RESTvehiclerental.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private StoreRepository store_repository;

    @Autowired
    private CarRepository car_repository;

    @GetMapping
    public @ResponseBody List<Customer> getCustomers() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public @ResponseBody Customer getOneById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/lastName/{lastName}")
    public ResponseEntity<List<Customer>> getByLastName(@PathVariable String lastName) {
        return new ResponseEntity<>(repository.findByLastName(lastName,Sort.by("lastName")), HttpStatus.OK);
    }

    @GetMapping("rental/{custId}")
    public List<Car> getRentals(@PathVariable Long custId) {
        return car_repository.findAllByRentals_customer_id(custId);
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
        if (updateData.stores != null) cust.stores.addAll(updateData.stores);

        return repository.save(cust);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer (@PathVariable Long id) {
         repository.deleteById(id);
    }



}
