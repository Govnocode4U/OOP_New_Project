package org.saloon_management.system_controller;

import org.modelmapper.ModelMapper;
import org.saloon_management.system.services.ClientService;
import org.saloon_management.system_models.Client;
import org.saloon_management.system_utils.ClientValidator;
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

    @RequestMapping(path = "/delete/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Integer id) {
        Optional<Client> optionalClient = clientService.getOneById(id);
        if (optionalClient.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        clientService.delete(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping("/update/{id}")
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
