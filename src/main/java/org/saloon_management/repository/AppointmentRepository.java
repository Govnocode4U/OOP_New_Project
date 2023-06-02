package org.saloon_management.repository;

import org.saloon_management.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    List<Appointment> findByDateTimeBetween(LocalDateTime fromDate, LocalDateTime toDate);
}
