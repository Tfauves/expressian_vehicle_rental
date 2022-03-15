package com.careerdevs.RESTvehiclerental.models.customer;

import com.careerdevs.RESTvehiclerental.models.auth.User;
import com.careerdevs.RESTvehiclerental.models.rental.Rental;
import com.careerdevs.RESTvehiclerental.models.store.Store;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Customer {
    @Id
    @GeneratedValue
    private Long id;
    private String fname;
    private String lname;
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

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;

    public Customer() {}

    public Customer(String firstName, String lastName, String email, User user) {
        this.fname = firstName;
        this.lname = lastName;
        this.email = email;
        this.user = user;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
