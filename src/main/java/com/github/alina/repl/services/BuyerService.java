package com.github.alina.repl.services;

import com.github.alina.repl.models.dtos.BuyerDTO;
import com.github.alina.repl.models.dtos.PropertyDTO;

import java.util.List;

public interface BuyerService {
    BuyerDTO save(BuyerDTO buyerDTO);

    BuyerDTO update(BuyerDTO buyerDTO);

    BuyerDTO findById(Long id);

    List<PropertyDTO> addPropertyToFavorites(Long id, Long propertyId);
}
