package com.careerdevs.RESTvehiclerental.controllers;

import com.careerdevs.RESTvehiclerental.models.Employee;
import com.careerdevs.RESTvehiclerental.models.auth.User;
import com.careerdevs.RESTvehiclerental.repositories.EmployeeRepository;
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
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    UserService userService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public @ResponseBody
    List<Employee> getAll(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public @ResponseBody Employee getById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

//    @GetMapping("/self")
//    public @ResponseBody
//    Employee getSelf() {
//        User currentUser = userService.getCurrentUser();
//
//        if (currentUser == null) {
//            return null;
//        }
//
//        return repository.findByEmployee_id(currentUser.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee newEmployee) {

        User currentUser = userService.getCurrentUser();

        if (currentUser == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        newEmployee.setUser(currentUser);

        return new ResponseEntity<>(repository.save(newEmployee), HttpStatus.CREATED);
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public @ResponseBody Employee updateEmployee(@PathVariable Long id, @RequestBody Employee updateData) {

        User currentUser = userService.getCurrentUser();

        if (currentUser == null) {
            return null;
        }

        Employee employee = repository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (updateData.getName() != null) employee.setName(updateData.getName());
        if (updateData.getDepartment() != null) employee.setDepartment(updateData.getDepartment());

        return repository.save(employee);
    }

//    @DeleteMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<String> destroyEmployee(@PathVariable Long id) {
//        User currentUser = userService.getCurrentUser();
//
//        if (currentUser == null) {
//            return null;
//        }
//        repository.deleteUserBy_id(currentUser.getId());
//        return new ResponseEntity<>("Deleted", HttpStatus.OK);
//    }
}
