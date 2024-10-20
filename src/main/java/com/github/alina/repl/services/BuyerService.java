package com.github.alina.repl.services;

import com.github.alina.repl.exceptions.ResourceNotFoundException;
import com.github.alina.repl.models.dtos.BuyerDTO;
import com.github.alina.repl.models.entities.Buyer;
import com.github.alina.repl.repositories.BuyerRepository;
import org.springframework.stereotype.Service;

@Service
public class BuyerService {
    private final BuyerRepository buyerRepository;

    public BuyerService(BuyerRepository buyerRepository) {
        this.buyerRepository = buyerRepository;
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
}
