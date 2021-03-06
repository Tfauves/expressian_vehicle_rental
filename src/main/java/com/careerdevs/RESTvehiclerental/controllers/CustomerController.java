package com.careerdevs.RESTvehiclerental.controllers;


import com.careerdevs.RESTvehiclerental.models.auth.User;
import com.careerdevs.RESTvehiclerental.models.customer.Customer;
import com.careerdevs.RESTvehiclerental.repositories.CarRepository;
import com.careerdevs.RESTvehiclerental.repositories.CustomerRepository;
import com.careerdevs.RESTvehiclerental.repositories.StoreRepository;
import com.careerdevs.RESTvehiclerental.repositories.UserRepository;
import com.careerdevs.RESTvehiclerental.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @Autowired
     UserRepository user_repository;

    @Autowired
    UserService userService;

    @GetMapping
//    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public @ResponseBody List<Customer> getCustomers() {
        return repository.findAll();
    }

    @GetMapping("/self")
    public @ResponseBody Customer getSelf() {
        User currentUser = userService.getCurrentUser();

        if (currentUser == null) {
            return null;
        }

        return repository.findByUser_id(currentUser.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasRole('MODERATOR')")
    public @ResponseBody Customer getOneById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

//    @GetMapping("rental/{custId}")
//    @PreAuthorize("hasRole('MODERATOR')")
//    public List<Car> getRentals(@PathVariable Long custId) {
//        return car_repository.findAllByRentals_customer_id(custId);
//    }

    @PostMapping
//    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")

    public ResponseEntity<Customer> createCustomer(@RequestBody Customer newCustomer) {
        User currentUser = userService.getCurrentUser();

        if (currentUser == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        newCustomer.setUser(currentUser);

        return new ResponseEntity<>(repository.save(newCustomer), HttpStatus.CREATED) ;
    }

    @PutMapping
//    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public @ResponseBody Customer updateCustomer(@PathVariable long id, @RequestBody Customer updateData) {

        User currentUser = userService.getCurrentUser();

        if (currentUser == null) {
            return null;
        }

        Customer customer = repository.findByUser_id(currentUser.getId()) .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (updateData.getfName() != null) customer.setfName(updateData.getfName());
        if (updateData.getlName() != null) customer.setlName(updateData.getlName());
        if (updateData.getEmail() != null) customer.setEmail(updateData.getEmail());
        if (updateData.stores != null) customer.stores.addAll(updateData.stores);

        return repository.save(customer);
    }

//    @DeleteMapping
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<String> deleteCustomer (@PathVariable Long id) {
//        User currentUser = userService.getCurrentUser();
//
//        if (currentUser == null) {
//            return null;
//        }
//         repository.deleteUserBy_id(currentUser.getId());
//        return new ResponseEntity<>("Deleted", HttpStatus.OK);
//    }



}
