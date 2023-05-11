package org.saloon_management.system_controller;


import org.modelmapper.ModelMapper;
import org.saloon_management.system.services.MasterService;
import org.saloon_management.system_models.Master;
import org.saloon_management.system_utils.validators.MasterValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/master")
public class MasterController {
    @Autowired
    private MasterService masterService;

    @Autowired
    private MasterValidator masterValidator;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(path = "/add")
    public ResponseEntity<?> addNewMaster(@ModelAttribute Master masterDTO, BindingResult bindingResult) {
        masterValidator.validate(masterDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        Master master = new Master(masterDTO.getFullName(), masterDTO.getSpecialization());
        masterService.add(master);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(path = "/delete/{id}")
    public ResponseEntity<?> deleteMaster(@PathVariable Integer id) {
        Optional<Master> optionalMaster = masterService.getOneById(id);
        if (optionalMaster.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        masterService.delete(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping("/update/{id}")
    public ResponseEntity<?> updateMaster(@PathVariable Integer id, @ModelAttribute Master masterDTO) {
        Optional<Master> optionalMaster = masterService.getOneById(id);
        if (optionalMaster.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        modelMapper.map(masterDTO, optionalMaster.get());
        Master master = optionalMaster.get();
        masterService.add(master);
        return ResponseEntity.ok("Master updated successfully");
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Master> getAllMasters() {
        return masterService.getAll();
    }
}
