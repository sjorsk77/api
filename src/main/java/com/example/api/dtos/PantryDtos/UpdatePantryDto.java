package com.example.api.dtos.PantryDtos;

import com.example.api.Enums.StorageTypes;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePantryDto {
    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "token is required")
    private StorageTypes storageType;
}
