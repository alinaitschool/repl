package com.github.alina.repl.services;

import com.github.alina.repl.models.entities.Property;
import com.github.alina.repl.models.entities.PropertyType;
import org.springframework.data.jpa.domain.Specification;

public class PropertySpecification {
    //propertyType, String city, Long price, int bedrooms
    public static Specification<Property> propertyTypeContains(PropertyType propertyType){
    return (property, query, criteriaBuilder)-> propertyType == null ? null :
            criteriaBuilder.like(criteriaBuilder.lower(property.get("propertyType")), "%" + propertyType.toString().toLowerCase() +"%");
    }
    public static Specification<Property> cityContains(String city){
        return (property, query, criteriaBuilder)-> city == null ? null :
                criteriaBuilder.like(criteriaBuilder.lower(property.get("city")), "%" +city.toLowerCase() +"%");
    }
    public static Specification<Property> priceBigger(Long price){
        return (property, query, criteriaBuilder)-> price == null ? null :
                criteriaBuilder.greaterThanOrEqualTo(property.get("price"), price);
    }
    public static Specification<Property> bedroomsNumbersContains(Long bedrooms){
        return (property, query, criteriaBuilder)-> bedrooms == null ? null :
                criteriaBuilder.equal(property.get("bedrooms"), bedrooms);
    }
}
