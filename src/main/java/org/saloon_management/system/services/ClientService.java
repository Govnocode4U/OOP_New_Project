package org.saloon_management.system.services;

import org.saloon_management.system_models.Client;

import java.util.List;

public interface ClientService {
    List<Client> getAll();

    Client getOne(String fullName);

    void add(Client client);
}
