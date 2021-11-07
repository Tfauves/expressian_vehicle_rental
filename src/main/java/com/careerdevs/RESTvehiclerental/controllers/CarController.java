package com.careerdevs.RESTvehiclerental.controllers;


import com.careerdevs.RESTvehiclerental.models.Car;
import com.careerdevs.RESTvehiclerental.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
//
//Create the following routes
//        Update one by id
//        destroy one by id
//        read all by column(field)




@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarRepository repository;

    @GetMapping
    public @ResponseBody List<Car> getCars() {
        return repository.findAll();
    }


    @GetMapping("/{id}")
    public @ResponseBody Car getOneById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Car> createCar(@RequestBody Car newCar) {
        return new ResponseEntity<>(repository.save(newCar), HttpStatus.CREATED) ;
    }

    @PutMapping("{id}")
    public @ResponseBody Car updateCarById(@PathVariable Long id,@RequestBody Car updateData) {
        Car car = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (updateData.getMake() != null) car.setMake(updateData.getMake());
        if (updateData.getModel() != null)  car.setModel(updateData.getModel());
        if (updateData.getCurrentOdometer() != null) car.setCurrentOdometer(updateData.getCurrentOdometer());
        if (updateData.getHasFullGas() != null) car.setHasFullGas(updateData.getHasFullGas());

        return repository.save(car);
    }


}
