package com.github.alina.repl.repositories;

import com.github.alina.repl.models.dtos.PropertyDTO;
import com.github.alina.repl.models.entities.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, Long> {
}
