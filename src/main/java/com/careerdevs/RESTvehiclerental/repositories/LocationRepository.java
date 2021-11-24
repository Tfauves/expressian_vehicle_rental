package com.careerdevs.RESTvehiclerental.repositories;

import com.careerdevs.RESTvehiclerental.models.location.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
//    find Locations with null vehicle
}
