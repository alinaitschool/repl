package com.github.alina.repl.models.dtos;
import com.github.alina.repl.models.entities.Buyer;
import com.github.alina.repl.models.entities.Property;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuyerDTO {
    private Long id;
    @NotNull
    private String firstName;
    @NotNull
    private String secondName;
    @NotNull
    @Email
    private String email;
    private List<PropertyDTO> favoriteProperties;

    public BuyerDTO(Long id, String firstName, String secondName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
    }

    public static BuyerDTO from(Buyer buyer) {
        return new BuyerDTO(buyer.getId(), buyer.getFirstName(), buyer.getSecondName(), buyer.getEmail());
    }
}
