package com.careerdevs.RESTvehiclerental.controllers;


import com.careerdevs.RESTvehiclerental.models.Store;
import com.careerdevs.RESTvehiclerental.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/stores")
public class StoreController {
    @Autowired
    private StoreRepository repository;

    @GetMapping
    public @ResponseBody List<Store> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<Store> createStore(@RequestBody Store newStore) {
        return new ResponseEntity<>(repository.save(newStore), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public @ResponseBody Store getById(@PathVariable long id, @RequestBody Store updateData) {
        Store store = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (updateData.getAddress() != null) store.setAddress(updateData.getAddress());
        if (updateData.getEmail() != null) store.setEmail(updateData.getEmail());

        return repository.save(store);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> destroyStore(@PathVariable Long id) {
        repository.deleteById(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }


}
