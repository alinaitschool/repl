package com.github.alina.repl.models.entities;

import com.github.alina.repl.models.dtos.PropertyDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "properties")
@EqualsAndHashCode(of = {"id"})
@Getter
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private PropertyType propertyType;
    private String description;
    private String address;
    private String city;
    private double price;
    private double surface;
    private int bedrooms;
    private int bathrooms;

    @ManyToOne
    private Agent agent;

    public static Property from(PropertyDTO propertyDTO) {
        return new Property(propertyDTO.getId(),
                propertyDTO.getTitle(),
                propertyDTO.getPropertyType(),
                propertyDTO.getDescription(),
                propertyDTO.getAddress(),
                propertyDTO.getCity(),
                propertyDTO.getPrice(),
                propertyDTO.getSurface(),
                propertyDTO.getBedrooms(),
                propertyDTO.getBathrooms(),
                Agent.from(propertyDTO.getAgent()));
    }
}
