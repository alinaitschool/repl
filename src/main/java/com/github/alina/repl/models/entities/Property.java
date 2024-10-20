package com.github.alina.repl.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "properties")
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
    private String type; // e.g., "House", "Apartment"
    private int bedrooms;
    private int bathrooms;

    @ManyToOne
    private Agent agent;
}
