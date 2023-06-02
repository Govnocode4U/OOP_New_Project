package org.saloon_management.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
public class Appointment {

    public Appointment() {

    }

    public Appointment(Client client, Master master, ServiceModel service, LocalDateTime dateTime) {
        this.client = client;
        this.master = master;
        this.service = service;
        this.dateTime = dateTime;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "master_id")
    private Master master;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private ServiceModel service;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    public Integer getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
    }

    public ServiceModel getService() {
        return service;
    }

    public void setService(ServiceModel service) {
        this.service = service;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getClientName() {
        return client.getFullName();
    }

    public String getMasterName() {
        return master.getFullName();
    }

    public String getServiceName() {
        return service.getName();
    }

    public String getServicePrice() {return service.getPrice();}
}

