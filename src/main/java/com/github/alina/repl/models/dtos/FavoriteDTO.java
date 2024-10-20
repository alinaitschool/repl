package com.github.alina.repl.models.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FavoriteDTO {
    @NotNull
    private Long propertyId;
}
