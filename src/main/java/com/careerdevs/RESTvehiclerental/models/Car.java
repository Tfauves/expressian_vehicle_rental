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
    private Boolean needsFuel;

    public Car () {}

    public Car (String make, String model, Integer currentOdometer, Boolean needsFuel) {
        this.make = make;
        this.model = model;
        this.currentOdometer = currentOdometer;
        this.needsFuel = needsFuel;
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

    public Boolean getNeedsFuel() {
        return needsFuel;
    }

    public void setNeedsFuel(Boolean needsFuel) {
        this.needsFuel = needsFuel;
    }
}
