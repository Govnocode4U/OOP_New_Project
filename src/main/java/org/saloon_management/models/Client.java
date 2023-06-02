package org.saloon_management.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clients")
public class Client extends Person {
    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    public Client() {
        super();
    }

    @OneToMany(mappedBy = "client", cascade = CascadeType.REMOVE)
    private List<Appointment> appointments = new ArrayList<>();

    public Client (String fullName, String phone, String email) {
        super(fullName);
        this.phone = phone;
        this.email = email;
    }

    public void setPhone(String newPhone) {
        phone = newPhone;
    }

    public void setEmail(String newEmail) {
        email = newEmail;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }
}


