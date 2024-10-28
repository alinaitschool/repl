package com.github.alina.repl.controllers;

import com.github.alina.repl.models.dtos.PropertyDTO;
import com.github.alina.repl.models.entities.Property;
import com.github.alina.repl.models.entities.PropertyType;
import com.github.alina.repl.services.PropertyService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/properties")
@RestController
public class PropertyController {
    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }
    @CrossOrigin(origins = "*")
    @Operation(summary = "Get all properties")
    @GetMapping
    public ResponseEntity<List<PropertyDTO>> getProperties(
            @RequestParam(value = "propertyType", required = false) PropertyType propertyType,
            @RequestParam(value = "city", required = false) String city,
            @RequestParam(value = "price", required = false) Long price,
            @RequestParam(value = "bedrooms", required = false) Long bedrooms) {
        return ResponseEntity.ok(propertyService.getProperties(propertyType, city, price, bedrooms));
    }
}
