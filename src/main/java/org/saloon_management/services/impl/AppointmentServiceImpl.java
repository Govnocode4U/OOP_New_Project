package org.saloon_management.services.impl;

import org.saloon_management.models.Appointment;
import org.saloon_management.models.Client;
import org.saloon_management.repository.AppointmentRepository;
import org.saloon_management.repository.ClientRepository;
import org.saloon_management.services.AppointmentService;
import org.saloon_management.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Transactional(readOnly = true)
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Override
    public List<Appointment> getAll() {
        return appointmentRepository.findAll();
    }

    @Override
    public Optional<Appointment> getOneById(Integer id) {
        return appointmentRepository.findById(id);
    }

    @Transactional
    @Override
    public void delete(Integer id ) {
        appointmentRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void add(Appointment appointment) {
        appointmentRepository.save(appointment);
    }

    @Override
    public List<Appointment> getAppointmentsBetweenDates(LocalDate fromDate, LocalDate toDate) {
        LocalDateTime fromDateTime = fromDate.atStartOfDay();
        LocalDateTime toDateTime = toDate.atTime(LocalTime.MAX);

        return appointmentRepository.findByDateTimeBetween(fromDateTime, toDateTime);
    }
}
