package com.careerdevs.RESTvehiclerental.repositories;

import com.careerdevs.RESTvehiclerental.models.customer.Customer;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByLastName(String lastName, Sort sort);
    List<Customer>findAllByRentals_car_id(Long id);


    Optional<Customer> findByUser_id(Long id);

//    void deleteUserBy_id(Long id);

}
