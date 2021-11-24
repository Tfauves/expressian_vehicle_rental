package com.careerdevs.RESTvehiclerental.models.vehicle;


import com.careerdevs.RESTvehiclerental.models.location.Location;
import com.careerdevs.RESTvehiclerental.models.store.Store;

import javax.persistence.*;
//
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id"
//)
@Entity
public class Car {
    @Id @GeneratedValue private Long id;
    private String make;
    private String model;
    private String color;
    private Integer year;
    private Integer currentOdometer;

    @ManyToOne
    @JoinColumn(name = "store_id", referencedColumnName = "id")
    private Store store;

    @OneToOne
    private Location location;

    public Car () {}

    public Car (Store store, String make, String model, String color, Integer year, Integer currentOdometer, Location location) {
        this.store = store;
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
}
