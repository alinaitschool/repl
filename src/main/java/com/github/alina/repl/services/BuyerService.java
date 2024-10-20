package com.github.alina.repl.services;

import com.github.alina.repl.exceptions.ResourceNotFoundException;
import com.github.alina.repl.models.dtos.BuyerDTO;
import com.github.alina.repl.models.dtos.PropertyDTO;
import com.github.alina.repl.models.entities.Buyer;
import com.github.alina.repl.models.entities.Property;
import com.github.alina.repl.repositories.BuyerRepository;
import com.github.alina.repl.repositories.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuyerService {
    private final BuyerRepository buyerRepository;
    private final PropertyRepository propertyRepository;

    public BuyerService(BuyerRepository buyerRepository, PropertyRepository propertyRepository) {
        this.buyerRepository = buyerRepository;
        this.propertyRepository = propertyRepository;
    }

    public BuyerDTO save(BuyerDTO buyerDTO) {
        return BuyerDTO.from(buyerRepository.save(Buyer.from(buyerDTO)));
    }

    public BuyerDTO update(BuyerDTO buyerDTO) {
        if (!buyerRepository.existsById(buyerDTO.getId())) {
            throw new ResourceNotFoundException("Buyer with id " + buyerDTO.getId() + " not found");
        }
        return BuyerDTO.from(buyerRepository.save(Buyer.from(buyerDTO)));
    }

    public BuyerDTO findById(Long id) {
        return BuyerDTO.from(buyerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Buyer with id " + id + " not found")));
    }

    public List<PropertyDTO> addPropertyToFavorites(Long id, Long propertyId) {
        Buyer buyer = buyerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Buyer with id " + propertyId + " not found"));
        Property property = propertyRepository.findById(propertyId).orElseThrow(() -> new ResourceNotFoundException("Property with id " + propertyId + " not found"));
        buyer.getFavoriteProperties().add(property);
        Buyer saved = buyerRepository.save(buyer);
        return saved.getFavoriteProperties().stream().map(PropertyDTO::from).toList();
    }
}
