package com.careerdevs.RESTvehiclerental.controllers;

import com.careerdevs.RESTvehiclerental.models.Employee;
import com.careerdevs.RESTvehiclerental.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository repository;

    @GetMapping
    public @ResponseBody
    List<Employee> getAll(){
        return repository.findAll();
    }
}
