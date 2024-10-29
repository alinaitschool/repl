package com.github.alina.repl.models.entities;

import com.github.alina.repl.models.dtos.PropertyDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "properties")
@EqualsAndHashCode(of = {"id"})
@Getter
@Setter
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Enumerated(EnumType.STRING)
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

    public Property(Long id,
                    String title,
                    PropertyType propertyType,
                    String description,
                    String address,
                    String city,
                    double price,
                    double surface,
                    int bedrooms,
                    int bathrooms) {
        this.id = id;
        this.title = title;
        this.propertyType = propertyType;
        this.description = description;
        this.address = address;
        this.city = city;
        this.price = price;
        this.surface = surface;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
    }

    public static Property fromDTOToEntity(PropertyDTO propertyDTO) {
        return new Property(propertyDTO.getId(),
                propertyDTO.getTitle(),
                propertyDTO.getPropertyType(),
                propertyDTO.getDescription(),
                propertyDTO.getAddress(),
                propertyDTO.getCity(),
                propertyDTO.getPrice(),
                propertyDTO.getSurface(),
                propertyDTO.getBedrooms(),
                propertyDTO.getBathrooms());
    }
}
