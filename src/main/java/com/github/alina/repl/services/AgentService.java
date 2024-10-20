package com.github.alina.repl.services;

import com.github.alina.repl.exceptions.ResourceNotFoundException;
import com.github.alina.repl.models.dtos.AgentDTO;
import com.github.alina.repl.models.entities.Agent;
import com.github.alina.repl.repositories.AgentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgentService {

    private final AgentRepository agentRepository;

    public AgentService(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    public AgentDTO save(AgentDTO agentDTO) {
        return AgentDTO.from(agentRepository.save(Agent.from(agentDTO)));
    }

    public AgentDTO update(AgentDTO agentDTO) {
        if (!agentRepository.existsById(agentDTO.getId())) {
            throw new ResourceNotFoundException("Agent with id " + agentDTO.getId() + " not found");
        }
        return AgentDTO.from(agentRepository.save(Agent.from(agentDTO)));
    }

    public AgentDTO findById(Long id) {
        return AgentDTO.from(agentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("/agent/" + id)));
    }

}
