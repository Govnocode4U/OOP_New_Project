package org.saloon_management.system.services.impl;

import org.saloon_management.repository.MasterRepository;
import org.saloon_management.system.services.MasterService;
import org.saloon_management.system_models.Master;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MasterServiceImpl implements MasterService {
    @Autowired
    private MasterRepository masterRepository;
    @Override
    public List<Master> getAll() {
        return masterRepository.findAll();
    }

    @Override
    public Master getOne(String fullName) {
        return masterRepository.findMasterByFullName(fullName);
    }

    @Override
    public Optional<Master> getOneById(Integer id) {
        return masterRepository.findById(id);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        masterRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void add(Master master) {
        masterRepository.save(master);
    }
}
