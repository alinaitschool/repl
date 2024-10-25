package services;

import com.github.alina.repl.exceptions.ResourceNotFoundException;
import com.github.alina.repl.models.dtos.BuyerDTO;
import com.github.alina.repl.models.dtos.PropertyDTO;
import com.github.alina.repl.models.entities.Buyer;
import com.github.alina.repl.models.entities.Property;
import com.github.alina.repl.models.entities.PropertyType;
import com.github.alina.repl.repositories.BuyerRepository;
import com.github.alina.repl.repositories.PropertyRepository;
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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BuyerServiceTest {
    @Mock
    private BuyerRepository buyerRepository;
    @Mock
    private PropertyRepository propertyRepository;

    @InjectMocks
    private BuyerServiceImpl buyerServiceImpl;

    @Test
    void testCreateBuyer() {
        BuyerDTO buyerDTO = new BuyerDTO("Alina", "Ghetler", "alina@gmail");
        Buyer buyer = Buyer.fromDTOToEntity(buyerDTO);
        //new Buyer(null, "Alina", "Ghetler", "alina@gmail");
        when(buyerRepository.save(buyer)).thenReturn(new Buyer(1L, "Alina", "Ghetler", "alina@gmail"));
        BuyerDTO buyerDTO1 = buyerServiceImpl.save(buyerDTO);
        buyerDTO.setId(1L);
        assertEquals(buyerDTO, buyerDTO1);
    }

    @Test
    void updateBuyer_Success() {
        BuyerDTO buyerDTO = new BuyerDTO("Alina", "Ghetler", "alina@gmail");
        buyerDTO.setId(1L);
        Buyer buyer = Buyer.fromDTOToEntity(buyerDTO);
        when(buyerRepository.existsById(buyerDTO.getId())).thenReturn(true);
        when(buyerRepository.save(any(Buyer.class))).thenReturn(buyer);
        BuyerDTO updatedBuyerDTO = buyerServiceImpl.update(buyerDTO);
        assertEquals(buyerDTO, updatedBuyerDTO);

    }

    @Test
    void updateBuyer_NotFound() {
        BuyerDTO buyerDTO = new BuyerDTO("Alina", "Ghetler", "alina@gmail");
        buyerDTO.setId(1L);
        Buyer buyer = Buyer.fromDTOToEntity(buyerDTO);
        when(buyerRepository.existsById(buyerDTO.getId())).thenReturn(false);
        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class, () -> buyerServiceImpl.update(buyerDTO));
    }

    @Test
    void findById_Success() {
        BuyerDTO buyerDTO = new BuyerDTO("Alina", "Ghetler", "alina@gmail");
        buyerDTO.setId(1L);
        Buyer buyer = Buyer.fromDTOToEntity(buyerDTO);
        when(buyerRepository.findById(buyerDTO.getId())).thenReturn(Optional.of(buyer));
        BuyerDTO result = buyerServiceImpl.findById(1L);
        assertEquals(buyerDTO, result);
    }

    @Test
    void testAddProperty() {
        BuyerDTO buyerDTO = new BuyerDTO("Alina", "Ghetler", "alina@gmail");
        buyerDTO.setId(1L);
        Buyer buyer = Buyer.fromDTOToEntity(buyerDTO);
        PropertyDTO propertyDTO = new PropertyDTO(1L, "Villa Nova", PropertyType.VILLA, "Beautifull", "Berlin", "Alina", 200.00, 20.5, 4, 5,
                null);
        Property property = Property.fromDTOToEntity(propertyDTO);
        when(buyerRepository.findById(buyerDTO.getId())).thenReturn(Optional.of(buyer));
        when(propertyRepository.findById(propertyDTO.getId())).thenReturn(Optional.of(property));
        when(buyerRepository.save(buyer)).thenReturn(buyer);
        List<PropertyDTO> result = buyerServiceImpl.addPropertyToFavorites(buyer.getId(), property.getId());
        assertEquals(Arrays.asList(propertyDTO), result);
    }
}