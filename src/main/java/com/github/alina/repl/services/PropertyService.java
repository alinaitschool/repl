package com.github.alina.repl.services;

import com.github.alina.repl.models.dtos.PropertyDTO;
import com.github.alina.repl.models.entities.PropertyType;

import java.util.List;

public interface PropertyService {
    List<PropertyDTO> getProperties(PropertyType propertyType, String city, Long price, int bedrooms);

}
