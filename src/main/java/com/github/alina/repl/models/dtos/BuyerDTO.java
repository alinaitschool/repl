package com.github.alina.repl.models.dtos;
import com.github.alina.repl.models.entities.Property;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.List;
@Data
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
}
