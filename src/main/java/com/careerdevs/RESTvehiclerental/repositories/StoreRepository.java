package com.careerdevs.RESTvehiclerental.repositories;


import com.careerdevs.RESTvehiclerental.models.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {

}
