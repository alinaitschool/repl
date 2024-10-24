package com.github.alina.repl.integrations.tests;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@AutoConfigureTestDatabase
public class PropertyControllerTest {
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
    void shouldPropertyBeFiltered() throws Exception {
        createProperty(PropertyType.VILLA, "Address 1", "Berlin");
        createProperty(PropertyType.FARMHOUSE, "Address 2", "Hamburg");
        mockMvc.perform(get("/api/properties").queryParam("propertyType", PropertyType.FARMHOUSE.name())
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    private void createProperty(PropertyType type, String address, String city) {
        Agent agent = agentRepository.save(new Agent(1L, "Alina", "alina@gmail"));
        Property property = new Property();
        property.setPropertyType(type);
        property.setCity(city);
        property.setAddress(address);
        property.setTitle("Demo");
        property.setAgent(agent);
        Property savedProperty = propertyRepository.save(property);
    }
}
