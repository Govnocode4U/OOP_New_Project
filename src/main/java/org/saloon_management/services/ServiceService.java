package org.saloon_management.services;

import org.saloon_management.models.ServiceModel;

import java.util.List;
import java.util.Optional;

public interface ServiceService {
    List<ServiceModel> getAll();

    ServiceModel getOne(String fullName);

    Optional<ServiceModel> getOneById(Integer id);

    void delete(Integer id);

    void add(ServiceModel service);
}
