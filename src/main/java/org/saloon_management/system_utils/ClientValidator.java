package org.saloon_management.system_utils;

import org.saloon_management.system.services.ClientService;
import org.saloon_management.system_models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ClientValidator implements Validator {
    @Autowired
    private ClientService clientService;
    @Override
    public boolean supports(Class<?> clazz) {
        return Client.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Client client = (Client) target;
        if (clientService.getOne(client.getFullName()) != null) {
            errors.rejectValue("name", "", "This name is already in use");
        }

    }
}
