package com.github.alina.repl.controllers;

import com.github.alina.repl.models.dtos.AgentDTO;
import com.github.alina.repl.services.AgentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
public class AgentController {

    private final AgentService agentService;

    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }

    @PostMapping("/")
    public ResponseEntity<AgentDTO> createAgent(@RequestBody @Valid AgentDTO agentDTO) {
        AgentDTO saved  = agentService.save(agentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgentDTO> updateAgent(@PathVariable Long id,
                                                @RequestBody @Valid AgentDTO agentDTO) {
        if (!id.equals(agentDTO.getId())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(agentService.save(agentDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgentDTO> getAgent(@PathVariable Long id) {
        return ResponseEntity.ok().body(agentService.findById(id));
    }

}
