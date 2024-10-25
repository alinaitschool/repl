package com.github.alina.repl.services;

import com.github.alina.repl.exceptions.ResourceNotFoundException;
import com.github.alina.repl.models.dtos.BuyerDTO;
import com.github.alina.repl.models.dtos.PropertyDTO;
import com.github.alina.repl.models.entities.Buyer;
import com.github.alina.repl.models.entities.Property;
import com.github.alina.repl.repositories.BuyerRepository;
import com.github.alina.repl.repositories.PropertyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BuyerServiceImpl implements BuyerService {
    private final BuyerRepository buyerRepository;
    private final PropertyRepository propertyRepository;

    public BuyerServiceImpl(BuyerRepository buyerRepository, PropertyRepository propertyRepository) {
        this.buyerRepository = buyerRepository;
        this.propertyRepository = propertyRepository;
    }

    @Override
    public BuyerDTO save(BuyerDTO buyerDTO) {
        Buyer buyer = buyerRepository.save(Buyer.fromDTOToEntity(buyerDTO));
        log.info("Buyer with the id {} was saved", buyer.getId());
        return BuyerDTO.fromEntityToDTO(buyer);
    }

    @Override
    public BuyerDTO update(BuyerDTO buyerDTO) {
        if (!buyerRepository.existsById(buyerDTO.getId())) {
            throw new ResourceNotFoundException("Buyer with id " + buyerDTO.getId() + " not found");
        }
        Buyer buyer = buyerRepository.save(Buyer.fromDTOToEntity(buyerDTO));
        log.info("Buyer with the id {} was updated", buyer.getId());
        return BuyerDTO.fromEntityToDTO(buyer);
    }

    @Override
    public BuyerDTO findById(Long id) {
        Buyer buyer = buyerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Buyer with id " + id + " not found"));
        log.info("Buyer with the id {} was find it", buyer.getId());
        return BuyerDTO.fromEntityToDTO(buyer);
    }

    @Override
    public List<PropertyDTO> addPropertyToFavorites(Long id, Long propertyId) {
        Buyer buyer = buyerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Buyer with id " + propertyId + " not found"));
        Property property = propertyRepository.findById(propertyId).orElseThrow(() -> new ResourceNotFoundException("Property with id " + propertyId + " not found"));
        buyer.getFavoriteProperties().add(property);
        Buyer saved = buyerRepository.save(buyer);
        log.info("Property with the id {} was add it to the property list", property.getId());
        return saved.getFavoriteProperties().stream().map(PropertyDTO::fromEntityToDTO).toList();
    }
}
