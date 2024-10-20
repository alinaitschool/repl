package com.github.alina.repl.models.dtos;

import com.github.alina.repl.models.entities.Property;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;
@Data
@Table(name = "agents")
public class AgentDTO {
    private Long id;
    private String name;
    private String email;

    @OneToMany(mappedBy = "agent")
    private List<Property> properties;

    public void setId(Long id) {
        this.id = id;
    }
}
