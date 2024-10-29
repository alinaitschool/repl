package com.github.alina.repl.controllers;

import com.github.alina.repl.models.dtos.BuyerDTO;
import com.github.alina.repl.models.dtos.FavoriteDTO;
import com.github.alina.repl.models.dtos.PropertyDTO;
import com.github.alina.repl.services.BuyerService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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

@CrossOrigin(origins = "*")
@Validated
@RestController
@RequestMapping("/api/buyers")
public class BuyerController {

    private final BuyerService buyerService;

    public BuyerController(BuyerService buyerService) {
        this.buyerService = buyerService;
    }

    @Operation(summary = "Create a buyer")
    @PostMapping
    public ResponseEntity<BuyerDTO> createBuyer(@RequestBody @Valid BuyerDTO buyerDTO) {
        BuyerDTO saved = buyerService.save(buyerDTO);
        return ResponseEntity.created(URI.create("/api/buyers/" + saved.getId())).body(saved);
    }

    @Operation(summary = "Update a buyer")
    @PutMapping("/{id}")
    public ResponseEntity<BuyerDTO> updateBuyer(@PathVariable Long id,
                                                @RequestBody @Valid BuyerDTO buyerDTO) {
        if (!id.equals(buyerDTO.getId())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(buyerService.update(buyerDTO));
    }

    @Operation(summary = "Get agent")
    @GetMapping("/{id}")
    public ResponseEntity<BuyerDTO> getAgent(@PathVariable Long id) {
        return ResponseEntity.ok().body(buyerService.findById(id));
    }

    @Operation(summary = "Add a property to favourites")
    @PatchMapping("/{id}")
    public ResponseEntity<List<PropertyDTO>> addPropertyToFavorites(@PathVariable Long id, @RequestBody @Valid FavoriteDTO favorite) {
        List<PropertyDTO> propertyDTOS = buyerService.addPropertyToFavorites(id, favorite.getPropertyId());
        return ResponseEntity.ok().body(propertyDTOS);
    }

    @Operation(summary = "Delete a buyer by id")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteBuyer(@PathVariable("id") Long id) {
        buyerService.deleteBuyer(id);
        return ResponseEntity.ok("Buyer delete succesfull");
    }
}
