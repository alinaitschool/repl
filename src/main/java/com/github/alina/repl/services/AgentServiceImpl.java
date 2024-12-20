package com.github.alina.repl.services;

import com.github.alina.repl.exceptions.AgentNotFoundException;
import com.github.alina.repl.exceptions.IdNotMatchException;
import com.github.alina.repl.exceptions.ResourceNotFoundException;
import com.github.alina.repl.models.dtos.AgentDTO;
import com.github.alina.repl.models.dtos.PropertyDTO;
import com.github.alina.repl.models.entities.Agent;
import com.github.alina.repl.models.entities.Property;
import com.github.alina.repl.repositories.AgentRepository;
import com.github.alina.repl.repositories.PropertyRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
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
        log.info("Agent with was saved");
        return AgentDTO.fromEntityToDTO(agentRepository.save(Agent.fromDTOToEntity(agentDTO)));
    }

    @Override
    public AgentDTO update(Long id, AgentDTO agentDTO) {
        if (!id.equals(agentDTO.getId())) {
            log.error("Agent with id " + agentDTO.getId() + " not match the path");
            throw new IdNotMatchException("Agent with id " + agentDTO.getId() + " not match the path");
        }
        if (!agentRepository.existsById(agentDTO.getId())) {
            throw new ResourceNotFoundException("Agent with id " + agentDTO.getId() + " not found");
        }
        Agent saved = agentRepository.save(Agent.fromDTOToEntity(agentDTO));
        log.info("Agent with id {} was updated", id);
        return AgentDTO.fromEntityToDTO(saved);
    }

    @Override
    public AgentDTO findById(Long id) {
        log.info("Agent with id {} was updated", id);
        return AgentDTO.fromEntityToDTO(agentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("/agent/" + id)));
    }

    @Override
    public PropertyDTO addProperty(Long agentId, PropertyDTO propertyDTO) {
        Agent agent = agentRepository.findById(agentId).orElseThrow(() -> new ResourceNotFoundException("/agent/" + agentId));
        Property property = Property.fromDTOToEntity(propertyDTO);
        property.setAgent(agent);
        log.info("Property add it");
        return PropertyDTO.fromEntityToDTO(propertyRepository.save(property));
    }

    @Override
    @Transactional
    public void deleteAgent(Long id) {
        Agent agent = agentRepository.findById(id).orElseThrow(()->new AgentNotFoundException("Event with {id} not found"));
        propertyRepository.deleteBuyerRelation(id);
        propertyRepository.deleteAllByAgent_Id(id);
        agentRepository.deleteById(id);
    }
}
