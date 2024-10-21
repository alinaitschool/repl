package com.github.alina.repl.controllers;

import com.github.alina.repl.models.dtos.BuyerDTO;
import com.github.alina.repl.models.dtos.FavoriteDTO;
import com.github.alina.repl.models.dtos.PropertyDTO;
import com.github.alina.repl.services.BuyerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;


@Validated
@RestController
@RequestMapping("api/buyers")
public class BuyerController {

    private final BuyerService buyerService;

    public BuyerController(BuyerService buyerService) {
        this.buyerService = buyerService;
    }

    @PostMapping
    public ResponseEntity<BuyerDTO> createBuyer(@RequestBody @Valid BuyerDTO buyerDTO) {
        BuyerDTO saved = buyerService.save(buyerDTO);
        return ResponseEntity.created(URI.create(" " + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BuyerDTO> updateBuyer(@PathVariable Long id,
                                                @RequestBody @Valid BuyerDTO buyerDTO) {
        if (!id.equals(buyerDTO.getId())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(buyerService.update(buyerDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BuyerDTO> getAgent(@PathVariable Long id) {
        return ResponseEntity.ok().body(buyerService.findById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<List<PropertyDTO>> addPropertyToFavorites(@PathVariable Long id, @RequestBody @Valid FavoriteDTO favorite) {
        List<PropertyDTO> propertyDTOS = buyerService.addPropertyToFavorites(id, favorite.getPropertyId());
        return ResponseEntity.ok().body(propertyDTOS);
    }
}
