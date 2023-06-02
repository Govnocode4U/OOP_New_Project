package org.saloon_management.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "masters")
public class Master extends Person {

    @Column(name = "specialization")
    private String specialization;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "master_to_service",
            joinColumns = {@JoinColumn(name = "master_id")},
            inverseJoinColumns = {@JoinColumn(name = "service_id")}
    )
    private List<ServiceModel> services = new ArrayList<>();


    @OneToMany(mappedBy = "master", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Appointment> appointments = new ArrayList<>();

    public Master() {
        super();
    }

    public Master(String fullName, String specialization) {
        super(fullName);
        this.specialization = specialization;
    }

    public void deleteServiceFromList(ServiceModel serviceModel) {
        this.services.remove(serviceModel);
        serviceModel.getMasters().remove(this);
    }

    public void addServiceToList(ServiceModel serviceModel) {
        this.services.add(serviceModel);
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public List<ServiceModel> getServices() {
        return services;
    }

    public void setServices(List<ServiceModel> services) {
        this.services = services;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }
}
