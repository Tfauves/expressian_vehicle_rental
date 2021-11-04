package com.careerdevs.RESTvehiclerental.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Employee {
    @Id @GeneratedValue private Long id;
    private String firstName;
    private String lastName;
    private String department;
    private Boolean isCurrent;

    public Employee() {}

    public Employee(String firstName, String lastName, String department, Boolean isCurrent) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.isCurrent = isCurrent;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Boolean getCurrent() {
        return isCurrent;
    }

    public void setCurrent(Boolean current) {
        isCurrent = current;
    }
}
