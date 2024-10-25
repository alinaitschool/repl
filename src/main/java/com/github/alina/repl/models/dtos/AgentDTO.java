package com.github.alina.repl.models.dtos;

import com.github.alina.repl.models.entities.Agent;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
public class AgentDTO {
    private Long id;
    @NotNull
    private String name;
    @NotNull
    @Email
    private String email;
    private List<PropertyDTO> properties;

    public AgentDTO(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public static AgentDTO fromEntityToDTO(Agent agent) {
        if (agent == null) {
            return null;
        }
        return new AgentDTO(agent.getId(), agent.getName(), agent.getEmail());
    }
}
