package com.careerdevs.RESTvehiclerental.repositories;

import com.careerdevs.RESTvehiclerental.models.Car;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

   List<Car> findByMake(String make, Sort sort);
   List<Car> findByColor(String color, Sort sort);
   List<Car> findByStoreId(Long id, Sort sort);



}
