package org.saloon_management.utils.validators;

import org.saloon_management.models.Client;
import org.saloon_management.services.impl.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ClientValidator implements Validator {
    @Autowired
    private ClientServiceImpl clientService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Client.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Client client = (Client) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fullName", "", "Please enter a name.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "", "Please enter an email address.");

        if (client.getPhone() != null && !client.getPhone().matches("\\+7\\d{10}")) {
            errors.rejectValue("phone", "", "Invalid phone number format. Please use the format +7XXXXXXXXXX.");
        }

        if (clientService.getOne(client.getFullName()) != null) {
            errors.rejectValue("fullName", "", "This name is already in use.");
        }
    }
}
