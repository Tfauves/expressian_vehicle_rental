package com.careerdevs.RESTvehiclerental.controllers;

import com.careerdevs.RESTvehiclerental.models.Location;
import com.careerdevs.RESTvehiclerental.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/locations")
public class LocationController {

    @Autowired
    private LocationRepository repository;

    @GetMapping
    public @ResponseBody List<Location> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<Location> createLocation(@RequestBody Location newLocation) {
        return new ResponseEntity<>(repository.save(newLocation), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public @ResponseBody Location updateLocation(@PathVariable Long id, @RequestBody Location updates) {
        Location location = repository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (updates.getLocationDesignation() != null) location.setLocationDesignation(updates.getLocationDesignation());

        return repository.save(location);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> destroyLocation(@PathVariable Long id) {
        repository.deleteById(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

}
