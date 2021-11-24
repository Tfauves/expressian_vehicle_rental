package com.careerdevs.RESTvehiclerental.models.customer;

import com.careerdevs.RESTvehiclerental.models.rental.Rental;
import com.careerdevs.RESTvehiclerental.models.store.Store;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Customer {
    @Id @GeneratedValue private Long id;
    private String firstName;
    private String lastName;
    private String email;

    //store id in customer table
    @ManyToMany
    @JoinTable(
            name = "customer_id",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "store_id")
    )
    public Set<Store> stores;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Rental> rentals;

    public Customer() {}

    public Customer(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
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
