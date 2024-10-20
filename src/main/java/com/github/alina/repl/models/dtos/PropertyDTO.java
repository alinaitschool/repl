package com.github.alina.repl.models.dtos;

import com.github.alina.repl.models.entities.Agent;
import com.github.alina.repl.models.entities.PropertyType;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class PropertyDTO {
    private Long id;
    private String title;
    private PropertyType propertyType;
    private String description;
    private String address;
    private String city;
    private double price;
    private double surface;
    private String type; // e.g., "House", "Apartment"
    private int bedrooms;
    private int bathrooms;

    @ManyToOne
    private Agent agent;
}
