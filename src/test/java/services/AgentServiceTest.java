package services;

import com.github.alina.repl.models.dtos.AgentDTO;
import com.github.alina.repl.models.dtos.BuyerDTO;
import com.github.alina.repl.models.dtos.PropertyDTO;
import com.github.alina.repl.models.entities.Agent;
import com.github.alina.repl.models.entities.Buyer;
import com.github.alina.repl.models.entities.Property;
import com.github.alina.repl.models.entities.PropertyType;
import com.github.alina.repl.repositories.AgentRepository;
import com.github.alina.repl.repositories.BuyerRepository;
import com.github.alina.repl.repositories.PropertyRepository;
import com.github.alina.repl.services.AgentServiceImpl;
import com.github.alina.repl.services.BuyerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class AgentServiceTest {
    @Mock
    private AgentRepository agentRepository;
    @Mock
    private PropertyRepository propertyRepository;
    @InjectMocks
    private AgentServiceImpl agentServiceImpl;

    @Test
    void testCreateAgent(){
        AgentDTO agentDTO = new AgentDTO(null, "Gabriela",  "alina@gmail" );
        Agent agent = Agent.from(agentDTO);
        when(agentRepository.save(agent)).thenReturn(new Agent(1L, "Gabriela",  "alina@gmail"));
        AgentDTO agentDTO1 = agentServiceImpl.save(agentDTO);
        agentDTO.setId(1L);
        assertEquals(agentDTO, agentDTO1);
    }
    @Test
    void shouldAddPropertyByAgent(){
        AgentDTO agentDTO = new AgentDTO(1L, "Gabriela",  "alina@gmail" );
        Agent agent = Agent.from(agentDTO);
        PropertyDTO propertyDTO = new PropertyDTO(1L, "Villa Nova", PropertyType.VILLA, "Beautifull", "Berlin", "Alina", 200.00, 20.5, 4, 5,
                null);
        Property property = Property.from(propertyDTO);
        property.setAgent(agent);
        when(agentRepository.findById(agent.getId())).thenReturn(Optional.of(agent));
        when(propertyRepository.save(property)).thenReturn(property);
        PropertyDTO result = agentServiceImpl.addProperty(1L, propertyDTO );
        propertyDTO.setAgent(agentDTO);
        assertEquals(propertyDTO, result);
    }
}
