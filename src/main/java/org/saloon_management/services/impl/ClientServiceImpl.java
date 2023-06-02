package org.saloon_management.services.impl;

import org.saloon_management.repository.AppointmentRepository;
import org.saloon_management.repository.ClientRepository;
import org.saloon_management.services.ClientService;
import org.saloon_management.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Transactional(readOnly = true)
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    @Override
    public Optional<Client> getOneById(Integer id) {
        return clientRepository.findById(id);
    }

    @Transactional
    @Override
    public void delete(Client client) {
        clientRepository.deleteById(client.getId());
    }

    @Override
    public Client getOne(String fullName) {
        return clientRepository.findClientByFullName(fullName);
    }

    @Transactional
    @Override
    public void add(Client client) {
        clientRepository.save(client);
    }
}
