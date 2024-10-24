package com.github.alina.repl.services;

import com.github.alina.repl.exceptions.ResourceNotFoundException;
import com.github.alina.repl.models.dtos.AgentDTO;
import com.github.alina.repl.models.dtos.PropertyDTO;
import com.github.alina.repl.models.entities.Agent;
import com.github.alina.repl.models.entities.Property;
import com.github.alina.repl.repositories.AgentRepository;
import com.github.alina.repl.repositories.PropertyRepository;
import org.springframework.stereotype.Service;

@Service
public class AgentServiceImpl implements AgentService {

    private final AgentRepository agentRepository;
    private final PropertyRepository propertyRepository;

    public AgentServiceImpl(AgentRepository agentRepository, PropertyRepository propertyRepository) {
        this.agentRepository = agentRepository;
        this.propertyRepository = propertyRepository;
    }

    @Override
    public AgentDTO save(AgentDTO agentDTO) {
        return AgentDTO.from(agentRepository.save(Agent.from(agentDTO)));
    }

    @Override
    public AgentDTO update(AgentDTO agentDTO) {
        if (!agentRepository.existsById(agentDTO.getId())) {
            throw new ResourceNotFoundException("Agent with id " + agentDTO.getId() + " not found");
        }
        Agent saved = agentRepository.save(Agent.from(agentDTO));
        return AgentDTO.from(saved);
    }

    @Override
    public AgentDTO findById(Long id) {
        return AgentDTO.from(agentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("/agent/" + id)));
    }

    @Override
    public PropertyDTO addProperty(Long agentId, PropertyDTO propertyDTO) {
        Agent agent = agentRepository.findById(agentId).orElseThrow(() -> new ResourceNotFoundException("/agent/" + agentId));
        Property property = Property.from(propertyDTO);
        property.setAgent(agent);
        return PropertyDTO.from(propertyRepository.save(property));
    }
}
