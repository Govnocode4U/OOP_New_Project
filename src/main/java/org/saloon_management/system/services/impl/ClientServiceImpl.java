package org.saloon_management.system.services.impl;

import org.saloon_management.repository.ClientRepository;
import org.saloon_management.system.services.ClientService;
import org.saloon_management.system_models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository clientRepository;
    @Override
    public List<Client> getAll() {
        return clientRepository.findAll();
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
