package org.saloon_management.system_utils;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ModelMapperConfig extends ModelMapper {
    public ModelMapperConfig() {
        this.getConfiguration().setPropertyCondition(Conditions.isNotNull());
    }
}
