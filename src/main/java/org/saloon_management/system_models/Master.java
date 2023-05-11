package org.saloon_management.system_models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "masters")
public class Master extends Person {

    @Column(name = "specialization")
    private String specialization;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "master_to_service",
            joinColumns = {@JoinColumn(name = "master_id")},
            inverseJoinColumns = {@JoinColumn(name = "service_id")}
    )
    private Set<Service> services;
    @OneToMany(mappedBy = "master", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Appointment> appointments = new HashSet<>();

    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }

    public Master() {
        super();
    }

    public Master(String fullName, String specialization) {
        super(fullName);
        this.specialization = specialization;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public Set<Service> getServices() {
        return services;
    }

    public void setServices(Set<Service> services) {
        this.services = services;
    }
}

