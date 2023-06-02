package org.saloon_management.repository;

import org.saloon_management.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    Client findClientByFullName(String fullName);
}
