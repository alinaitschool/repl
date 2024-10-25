package com.github.alina.repl.integrations.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.alina.repl.models.dtos.PropertyDTO;
import com.github.alina.repl.models.entities.Agent;
import com.github.alina.repl.models.entities.Property;
import com.github.alina.repl.models.entities.PropertyType;
import com.github.alina.repl.repositories.AgentRepository;
import com.github.alina.repl.repositories.BuyerRepository;
import com.github.alina.repl.repositories.PropertyRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@AutoConfigureTestDatabase
public class AgentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private BuyerRepository buyerRepository;
    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private AgentRepository agentRepository;

    @Test
    void testAddFavouriteProperty() throws Exception {
        Agent agent = agentRepository.save(new Agent(null, "Alina", "alina@gmail"));
        Property property = new Property();
        property.setPropertyType(PropertyType.VILLA);
        property.setCity("Berlin");
        property.setTitle("Title");
        property.setAddress("cucu");
        PropertyDTO propertyDTO= PropertyDTO.fromEntityToDTO(property);
        mockMvc.perform(post("/api/agents/"+ agent.getId() + "/properties").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(propertyDTO))).andExpect(status().isCreated());
    }
}