package org.saloon_management.system_models;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "full_name")
    private String fullName;

    public Person() {

    }

    public Person(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public int getId() {
        return id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}


