package org.saloon_management.repository;

import org.saloon_management.models.Master;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MasterRepository extends JpaRepository<Master, Integer> {
    Master findMasterByFullName(String fullName);
}
