package com.github.alina.repl.models.entities;

import com.github.alina.repl.models.dtos.BuyerDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@EqualsAndHashCode(of = {"id"})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Buyer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String secondName;

    private String email;

    @ManyToMany
    private List<Property> favoriteProperties = new ArrayList<>();

    public Buyer(Long id, String firstName, String secondName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
    }

    public static Buyer fromDTOToEntity(BuyerDTO buyerDTO) {
        if (buyerDTO == null) {
            return null;
        }
        return new Buyer(buyerDTO.getId(), buyerDTO.getFirstName(), buyerDTO.getSecondName(), buyerDTO.getEmail());
    }
}
