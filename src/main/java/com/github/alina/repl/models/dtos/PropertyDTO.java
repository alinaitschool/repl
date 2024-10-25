package com.github.alina.repl.models.dtos;

import com.github.alina.repl.models.entities.Property;
import com.github.alina.repl.models.entities.PropertyType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class PropertyDTO {
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private PropertyType propertyType;
    private String description;
    @NotNull
    private String address;
    @NotNull
    private String city;
    private double price;
    private double surface;
    private int bedrooms;
    private int bathrooms;
    private AgentDTO agent;

    public static PropertyDTO fromEntityToDTO(Property property) {
        return new PropertyDTO(property.getId(),
                property.getTitle(),
                property.getPropertyType(),
                property.getDescription(),
                property.getAddress(),
                property.getCity(),
                property.getPrice(),
                property.getSurface(),
                property.getBedrooms(),
                property.getBathrooms(),
                AgentDTO.fromEntityToDTO(property.getAgent()));
    }
}
