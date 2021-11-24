package com.careerdevs.RESTvehiclerental.models.vehicle;


import com.careerdevs.RESTvehiclerental.models.customer.Customer;
import com.careerdevs.RESTvehiclerental.models.location.Location;
import com.careerdevs.RESTvehiclerental.models.rental.Rental;
import com.careerdevs.RESTvehiclerental.models.store.Store;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import javax.persistence.*;
import java.util.Set;

//
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id"
//)
@Entity
public class Car {
    @Id
    @GeneratedValue
    private Long id;
    private String make;
    private String model;
    private String color;
    private Integer year;
    private Integer currentOdometer;

    @ManyToOne
    @JoinColumn(name = "store_id", referencedColumnName = "id")
    private Store store;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @JsonIgnoreProperties("email")
    private Customer customer;

    @OneToOne
    private Location location;

    @OneToMany(mappedBy = "car", fetch = FetchType.LAZY)
    @JsonIncludeProperties("customer")
    private Set<Rental> rentals;

    public Car () {}

    public Car (Store store, Customer customer, String make, String model, String color, Integer year, Integer currentOdometer, Location location) {
        this.store = store;
        this.customer = customer;
        this.make = make;
        this.model = model;
        this.color = color;
        this.year = year;
        this.currentOdometer = currentOdometer;
        this.location = location;

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getCurrentOdometer() {
        return currentOdometer;
    }

    public void setCurrentOdometer(Integer currentOdometer) {
        this.currentOdometer = currentOdometer;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(Set<Rental> rentals) {
        this.rentals = rentals;
    }
}
