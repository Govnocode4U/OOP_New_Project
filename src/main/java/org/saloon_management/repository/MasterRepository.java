package org.saloon_management.repository;

import org.saloon_management.system_models.Client;
import org.saloon_management.system_models.Master;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MasterRepository extends JpaRepository<Master, Integer> {
    Master findMasterByFullName(String fullName);
}
