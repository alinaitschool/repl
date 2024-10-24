package com.github.alina.repl.integrations.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.alina.repl.models.dtos.FavoriteDTO;
import com.github.alina.repl.models.entities.Agent;
import com.github.alina.repl.models.entities.Buyer;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@AutoConfigureTestDatabase
public class BuyerControllerTest {
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
        // setup test data
        Agent agent = agentRepository.save(new Agent(null, "Alina", "alina@gmail"));
        Buyer buyer = new Buyer();
        buyer.setFirstName("Alina");
        buyer.setSecondName("Ghetler");
        buyer.setEmail("alina@gmail.com");
        Buyer savedBuyer = buyerRepository.save(buyer);
        Property property = new Property();
        property.setPropertyType(PropertyType.VILLA);
        property.setCity("Berlin");
        property.setAgent(agent);
        Property savedProperty = propertyRepository.save(property);
        FavoriteDTO favoriteDTO = new FavoriteDTO();
        favoriteDTO.setPropertyId(savedProperty.getId());
        mockMvc.perform(patch("/api/buyers/" + savedBuyer.getId()).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsBytes(favoriteDTO))).andExpect(status().isOk());
    }
}
