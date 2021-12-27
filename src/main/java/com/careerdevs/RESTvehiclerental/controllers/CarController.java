package com.careerdevs.RESTvehiclerental.controllers;


import com.careerdevs.RESTvehiclerental.models.customer.Customer;
import com.careerdevs.RESTvehiclerental.models.rental.Rental;
import com.careerdevs.RESTvehiclerental.models.vehicle.Car;
import com.careerdevs.RESTvehiclerental.models.location.Location;
import com.careerdevs.RESTvehiclerental.repositories.CarRepository;
import com.careerdevs.RESTvehiclerental.repositories.LocationRepository;
import com.careerdevs.RESTvehiclerental.repositories.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarRepository repository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private RentalRepository rental_repository;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Iterable<Car>> getAll() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public @ResponseBody Car getOneById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/store/{storeId}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<Car>> getByStoreId(@PathVariable Long storeId) {
        return new ResponseEntity<>(repository.findByStoreId(storeId, Sort.by("make")), HttpStatus.OK);
    }

    @GetMapping("/color/{color}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<Car>> getByColor(@PathVariable String color) {
        return new ResponseEntity<>(repository.findByColor(color, Sort.by("make")), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Car> createCar(@RequestBody Car newCar) {
        return new ResponseEntity<>(repository.save(newCar), HttpStatus.CREATED);
    }

    @PostMapping("rental/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Car> rentalById(@PathVariable Long id, @RequestBody Customer customer) {
        Optional<Car> car = repository.findById(id);

        if (car.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        Rental newRental = new Rental(customer, car.get());
        rental_repository.save(newRental);
        return new ResponseEntity<>(car.get(), HttpStatus.CREATED);
    }

    @PutMapping("/location")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public Car addLocation(@RequestBody Car carCar) {
        Car car = repository.findById(carCar.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Location location = locationRepository.save(carCar.getLocation());
        car.setLocation(location);
        return repository.save(car);
    }

    @PutMapping("/location/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Car> editLocation(@PathVariable Long id, @RequestBody Car updateData) {
        Car car = repository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        car.setLocation(updateData.getLocation());
        car.getLocation().setLocationDesignation(car.getLocation().getLocationDesignation());
        return new ResponseEntity<>(repository.save(car), HttpStatus.ACCEPTED);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
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
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> destroyCar(@PathVariable Long id) {
        repository.deleteById(id);
        return new ResponseEntity<>("Delete", HttpStatus.OK);
    }

}
