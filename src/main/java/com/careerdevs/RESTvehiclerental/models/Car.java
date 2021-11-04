package com.careerdevs.RESTvehiclerental.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Car {
    @Id @GeneratedValue private Long id;
    private String make;
    private String model;
    private Integer currentOdometer;
    private Boolean hasFullGas;

    public Car () {}

    public Car (String make, String model, Integer currentOdometer, Boolean hasFullGas) {
        this.make = make;
        this.model = model;
        this.currentOdometer = currentOdometer;
        this.hasFullGas = hasFullGas;
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

    public Boolean getHasFullGas() {
        return hasFullGas;
    }

    public void setHasFullGas(Boolean hasFullGas) {
        this.hasFullGas = hasFullGas;
    }
}