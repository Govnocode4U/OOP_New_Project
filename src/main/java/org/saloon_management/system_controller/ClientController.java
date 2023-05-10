package org.saloon_management.system_controller;

import org.saloon_management.repository.ClientRepository;
import org.saloon_management.system.services.ClientService;
import org.saloon_management.system_models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/client")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping(path = "/add")
    public @ResponseBody String addNewUser(@RequestParam String name
            , @RequestParam String phone, @RequestParam String email) {

        Client n = new Client(name, phone, email);
        clientService.add(n);
        return "Saved";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Client> getAllUsers() {
        return clientService.getAll();
    }
}
