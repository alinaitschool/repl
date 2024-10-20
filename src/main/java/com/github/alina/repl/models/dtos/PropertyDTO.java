package com.github.alina.repl.models.dtos;

import com.github.alina.repl.models.entities.PropertyType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
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
}
