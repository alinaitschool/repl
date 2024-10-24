package com.github.alina.repl.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.alina.repl.models.dtos.PropertyDTO;
import com.github.alina.repl.models.entities.Property;
import com.github.alina.repl.models.entities.PropertyType;
import com.github.alina.repl.repositories.PropertyRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PropertyServiceImp implements PropertyService {
    private final PropertyRepository propertyRepository;
    private final ObjectMapper objectMapper;

    public PropertyServiceImp(PropertyRepository propertyRepository, ObjectMapper objectMapper) {
        this.propertyRepository = propertyRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<PropertyDTO> getProperties(PropertyType propertyType, String city, Long price, int bedrooms) {
       Specification<Property> spec = Specification.where(PropertySpecification.propertyTypeContains(propertyType))
               .and(PropertySpecification.cityContains(city))
               .and(PropertySpecification.priceBigger(price)).
               and(PropertySpecification.bedroomsNumbersContains((long) bedrooms));
       List<Property> properties = propertyRepository.findAll(spec);
        return properties.stream().map(property -> objectMapper.convertValue(property, PropertyDTO.class)).toList();
    }
}
