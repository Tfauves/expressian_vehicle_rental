package com.careerdevs.RESTvehiclerental.controllers;


import com.careerdevs.RESTvehiclerental.models.Car;
import com.careerdevs.RESTvehiclerental.models.Location;
import com.careerdevs.RESTvehiclerental.repositories.CarRepository;
import com.careerdevs.RESTvehiclerental.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarRepository repository;

    @Autowired
    private LocationRepository locationRepository;

    @GetMapping
    public ResponseEntity<Iterable<Car>> getAll() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public @ResponseBody Car getOneById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<Car>> getByStoreId(@PathVariable Long storeId) {
        return new ResponseEntity<>(repository.findByStoreId(storeId, Sort.by("make")), HttpStatus.OK);
    }

    @GetMapping("/color/{color}")
    public ResponseEntity<List<Car>> getByColor(@PathVariable String color) {
        return new ResponseEntity<>(repository.findByColor(color, Sort.by("make")), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Car> createCar(@RequestBody Car newCar) {
        System.out.println(newCar.getStore().getId());
        return new ResponseEntity<>(repository.save(newCar), HttpStatus.CREATED);
    }

    @PostMapping("/location")
    public Car addLocation(@RequestBody Car carCar) {
        Car car = repository.findById(carCar.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
       Location location = locationRepository.save(carCar.getLocation());
       car.setLocation(location);
       return repository.save(car);
    }

    @PutMapping("{id}")
    public @ResponseBody Car updateCarById(@PathVariable Long id,@RequestBody Car updateData) {
        Car car = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (updateData.getMake() != null) car.setMake(updateData.getMake());
        if (updateData.getModel() != null) car.setModel(updateData.getModel());
        if (updateData.getColor() != null) car.setColor(updateData.getColor());
        if (updateData.getYear() != null) car.setYear(updateData.getYear());
        if (updateData.getCurrentOdometer() != null) car.setCurrentOdometer(updateData.getCurrentOdometer());
        if (updateData.getStore() != null) car.setStore(updateData.getStore());
        return repository.save(car);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> destroyCar(@PathVariable Long id) {
        repository.deleteById(id);
        return new ResponseEntity<>("Delete", HttpStatus.OK);
    }

}
