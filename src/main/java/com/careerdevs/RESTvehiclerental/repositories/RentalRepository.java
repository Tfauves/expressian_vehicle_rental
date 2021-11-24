package com.careerdevs.RESTvehiclerental.repositories;

import com.careerdevs.RESTvehiclerental.models.rental.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Long> {
}
