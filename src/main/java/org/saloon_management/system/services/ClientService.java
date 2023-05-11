package org.saloon_management.system.services;

import org.saloon_management.system_models.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    List<Client> getAll();

    Client getOne(String fullName);
    Optional<Client> getOneById(Integer id);

    void delete(Integer id);

    void add(Client client);
}
