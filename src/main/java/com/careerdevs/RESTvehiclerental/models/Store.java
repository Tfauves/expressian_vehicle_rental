package com.careerdevs.RESTvehiclerental.models;


import javax.persistence.*;
import java.util.List;
import java.util.Set;

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

    @ManyToMany
    @JoinTable(
            name = "store_id",
            joinColumns = @JoinColumn(name = "store_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id")
    )
    Set<Customer> customers;

    public Store() {}

    public Store(String address, String email, Set<Customer> customers) {
        this.address = address;
        this.email = email;
        this.customers = customers;
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
