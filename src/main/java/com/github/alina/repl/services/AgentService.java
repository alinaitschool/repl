package com.github.alina.repl.services;

import com.github.alina.repl.models.dtos.AgentDTO;
import com.github.alina.repl.models.dtos.PropertyDTO;

public interface AgentService {
    AgentDTO save(AgentDTO agentDTO);

    AgentDTO update(AgentDTO agentDTO);

    AgentDTO findById(Long id);

    PropertyDTO addProperty(Long agentId, PropertyDTO propertyDTO);
}
