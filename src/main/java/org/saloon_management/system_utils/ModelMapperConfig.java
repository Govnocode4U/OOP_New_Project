package org.saloon_management.system_utils;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class ModelMapperConfig extends ModelMapper {
    public ModelMapperConfig() {
        this.getConfiguration().setPropertyCondition(Conditions.isNotNull());
    }
}
