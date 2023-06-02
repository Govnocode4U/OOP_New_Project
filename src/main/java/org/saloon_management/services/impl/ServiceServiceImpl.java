package org.saloon_management.services.impl;

import org.saloon_management.models.ServiceModel;
import org.saloon_management.repository.MasterRepository;
import org.saloon_management.repository.ServiceRepository;
import org.saloon_management.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Transactional(readOnly = true)
@Service
public class ServiceServiceImpl implements ServiceService {
    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public List<ServiceModel> getAll() {
        return serviceRepository.findAll();
    }

    @Override
    public Optional<ServiceModel> getOneById(Integer id) {
        return serviceRepository.findById(id);
    }

    @Transactional
    @Override
    public void delete(Integer id ) {
        serviceRepository.deleteById(id);
    }

    @Override
    public ServiceModel getOne(String fullName) {
        return serviceRepository.findServiceByName(fullName);
    }

    @Transactional
    @Override
    public void add(ServiceModel service) {
        serviceRepository.save(service);
    }
}
