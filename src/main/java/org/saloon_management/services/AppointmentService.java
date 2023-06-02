package org.saloon_management.services;

import org.saloon_management.models.Appointment;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AppointmentService {
    List<Appointment> getAll();

    Optional<Appointment> getOneById(Integer id);

    void delete(Integer id);

    void add(Appointment appointment);

    public List<Appointment> getAppointmentsBetweenDates(LocalDate fromDate, LocalDate toDate);
}
