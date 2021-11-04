package com.careerdevs.RESTvehiclerental.repositories;

import com.careerdevs.RESTvehiclerental.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
