package com.careerdevs.RESTvehiclerental.models;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String locationDesignation;

    public Location() {}

    public Location(String locationDesignation) {
        this.locationDesignation = locationDesignation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocationDesignation() {
        return locationDesignation;
    }

    public void setLocationDesignation(String locationDesignation) {
        this.locationDesignation = locationDesignation;
    }
}
