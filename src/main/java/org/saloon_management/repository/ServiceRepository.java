package org.saloon_management.repository;

import org.saloon_management.models.ServiceModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<ServiceModel, Integer> {
    ServiceModel findServiceByName(String fullName);
}
