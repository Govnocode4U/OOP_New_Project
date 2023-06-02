package org.saloon_management.utils.validators;

import org.saloon_management.services.MasterService;
import org.saloon_management.models.Client;
import org.saloon_management.models.Master;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;

@Component
public class MasterValidator implements Validator {
    @Autowired
    private MasterService masterService;
    @Override
    public boolean supports(Class<?> clazz) {
        return Client.class.equals(clazz);
    }

    @Override
    public void validate(@ModelAttribute Object target, Errors errors) {
        Master master = (Master) target;
        if (masterService.getOne(master.getFullName()) != null) {
            errors.rejectValue("full_Name", "", "This name is already in use");
            System.out.println("This name is already in use");
        }
        else {
            System.out.println("validError");
        }

    }
}
