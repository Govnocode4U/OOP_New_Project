package org.saloon_management.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "services")
public class ServiceModel {
    public void setId(Integer id) {
        this.id = id;
    }

    public ServiceModel() {
    }

    public ServiceModel(String name, String price) {
        this.name = name;
        this.price = price;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private String price;

    @ManyToMany(mappedBy = "services", fetch = FetchType.EAGER)
    private List<Master> masters = new ArrayList<>();

    @OneToMany(mappedBy = "service", orphanRemoval = true)
    private List<Appointment> appointments = new ArrayList<>();

    @PreRemove
    public void removeServiceAssociations() {
        for (Master master: this.masters) {
            master.getServices().remove(this);
        }

    }
    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<Master> getMasters() {
        return masters;
    }

    public void setMasters(List<Master> masters) {
        this.masters = masters;
    }

    public Integer getId() {
        return id;
    }

}