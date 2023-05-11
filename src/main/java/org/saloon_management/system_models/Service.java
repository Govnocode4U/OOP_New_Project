package org.saloon_management.system_models;

import jakarta.persistence.*;

import java.util.Set;


@Entity
@Table(name = "services")
public class Service {

    public Service() {

    }

    public Service(String name, Set<Master> masters) {
        this.name = name;
        this.masters = masters;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "services")
    private Set<Master> masters;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Master> getMasters() {
        return masters;
    }

    public void setMasters(Set<Master> masters) {
        this.masters = masters;
    }

    public Integer getId() {
        return id;
    }
}

