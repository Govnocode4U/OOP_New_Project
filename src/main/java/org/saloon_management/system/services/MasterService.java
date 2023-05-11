package org.saloon_management.system.services;

import org.saloon_management.system_models.Client;
import org.saloon_management.system_models.Master;

import java.util.List;
import java.util.Optional;

public interface MasterService {
    List<Master> getAll();

    Master getOne(String fullName);
    Optional<Master> getOneById(Integer id);

    void delete(Integer id);

    void add(Master master);
}
