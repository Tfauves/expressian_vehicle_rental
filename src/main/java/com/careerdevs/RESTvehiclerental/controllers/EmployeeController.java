package com.careerdevs.RESTvehiclerental.controllers;


import com.careerdevs.RESTvehiclerental.models.Employee;
import com.careerdevs.RESTvehiclerental.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository repository;

    @GetMapping
    public @ResponseBody List<Employee> getEmployees() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public @ResponseBody Employee getOneById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee newEmployee) {
        return new ResponseEntity<>( repository.save(newEmployee), HttpStatus.CREATED);
    }
}
