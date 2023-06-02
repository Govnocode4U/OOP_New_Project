package org.saloon_management.controller;

import org.modelmapper.ModelMapper;
import org.saloon_management.models.Client;
import org.saloon_management.services.ClientService;
import org.saloon_management.utils.validators.ClientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping(path = "/client")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientValidator clientValidator;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(path = "/add")
    public ResponseEntity<?> addNewClient(@ModelAttribute Client clientDTO, BindingResult bindingResult) {
        clientValidator.validate(clientDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        Client client = new Client(clientDTO.getFullName(), clientDTO.getPhone(), clientDTO.getEmail());
        clientService.add(client);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/delete/{fullName}")
    public ResponseEntity<?> deleteClient(@PathVariable String fullName) {
        Client client = clientService.getOne(fullName);
        if (client == null) {
            return ResponseEntity.notFound().build();
        }
        clientService.delete(client);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateClient(@PathVariable Integer id, @ModelAttribute Client clientDTO) {
        Optional<Client> optionalClient = clientService.getOneById(id);
        if (optionalClient.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        modelMapper.map(clientDTO, optionalClient.get());
        Client client = optionalClient.get();
        clientService.add(client);
        return ResponseEntity.ok("Client updated successfully");
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Client> getAllClients() {
        return clientService.getAll();
    }
}
