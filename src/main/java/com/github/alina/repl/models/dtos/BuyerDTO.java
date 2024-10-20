package com.github.alina.repl.models.dtos;
import com.github.alina.repl.models.entities.Property;
import lombok.Data;
import java.util.List;
@Data
public class BuyerDTO {
    private String firstName;
    private String secondName;
    private String email;
    private List<Property> favoriteProperties;
}
