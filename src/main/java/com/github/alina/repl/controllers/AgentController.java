package com.github.alina.repl.controllers;

import com.github.alina.repl.models.dtos.AgentDTO;
import com.github.alina.repl.models.dtos.PropertyDTO;
import com.github.alina.repl.services.AgentService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@Validated
@RestController
@RequestMapping("/api/agents")
public class AgentController {

    private final AgentService agentService;

    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }

    @Operation(summary = "Create a new agent")
    @PostMapping
    public ResponseEntity<AgentDTO> createAgent(@RequestBody @Valid AgentDTO agentDTO) {
        AgentDTO saved = agentService.save(agentDTO);
        return ResponseEntity.created(URI.create("/api/agents/" + saved.getId())).body(saved);
    }
    @Operation(summary = "Create a new agent")
    @PutMapping("/{id}")
    public ResponseEntity<AgentDTO> updateAgent(@PathVariable Long id,
                                                @RequestBody @Valid AgentDTO agentDTO) {
        return ResponseEntity.ok().body(agentService.update(id, agentDTO));
    }
    @Operation(summary = "Get a agent")
    @GetMapping("/{id}")
    public ResponseEntity<AgentDTO> getAgent(@PathVariable Long id) {
        return ResponseEntity.ok().body(agentService.findById(id));
    }

    @Operation(summary = "Add a property")
    @PostMapping("/{id}/properties")
    public ResponseEntity<PropertyDTO> addProperty(@PathVariable Long id, @RequestBody @Valid PropertyDTO propertyDTO) {
        PropertyDTO saved = agentService.addProperty(id, propertyDTO);
        return ResponseEntity.created(URI.create("/api/property/" + saved.getId())).body(saved);
    }
}
