package com.careerdevs.RESTvehiclerental.repositories;

import com.careerdevs.RESTvehiclerental.models.Customer;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByLastName(String lastName, Sort sort);
}
