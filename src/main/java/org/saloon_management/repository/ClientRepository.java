package org.saloon_management.repository;

import org.saloon_management.system_models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    Client findClientByFullName(String fullName);
}
