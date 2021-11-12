package com.careerdevs.RESTvehiclerental.models;


import javax.persistence.*;
import java.util.List;

@Entity
public class Store {
    @Id
    @GeneratedValue
    private Long id;
    private String address;
    private String email;

    @OneToMany
    @JoinColumn(name = "store_id", referencedColumnName = "id")
    private List<Car> cars;

    public Store() {}

    public Store(String address, String email) {
        this.address = address;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
