package com.careerdevs.RESTvehiclerental.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Customer {
    @Id @GeneratedValue private Long id;
    private String firstName;
    private String lastName;
    private String email;

    @ManyToMany
    @JoinTable(
            name = "customer_id",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "store_id")
    )
    public Set<Store> stores;
    public Customer() {}

    public Customer(String firstName, String lastName, String email, Set<Store> stores) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.stores =stores;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
