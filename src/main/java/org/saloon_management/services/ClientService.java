package org.saloon_management.services;

import org.saloon_management.models.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    List<Client> getAll();

    Client getOne(String fullName);
    Optional<Client> getOneById(Integer id);

    void delete(Client client);

    void add(Client client);

}
