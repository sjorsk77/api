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
public class CreatePantryDto {
    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "userId is required")
    private Long userId;

    @NotNull(message = "storageType is required")
    private StorageTypes storageType;
}
