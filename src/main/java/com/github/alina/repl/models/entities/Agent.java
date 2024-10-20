package com.github.alina.repl.models.entities;

import com.github.alina.repl.models.dtos.AgentDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "agents")
@EqualsAndHashCode(of = {"id"})
@Getter
public class Agent {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    @OneToMany(mappedBy = "agent")
    private List<Property> properties;

    public Agent(Long id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    public static Agent from(AgentDTO agentDTO)  {
        if (agentDTO == null) {
            return null;
        }
        return new Agent(agentDTO.getId(), agentDTO.getEmail(), agentDTO.getName());
    }
}
