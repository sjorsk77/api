package com.example.api.dtos.DietDtos;

import com.example.api.dtos.EntityDtos.DietTypeDto;
import com.example.api.entities.DietType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateDietRequest {

    @NotNull(message = "User id is required")
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    private Integer minCalories;
    private Integer maxCalories;

    private List<Long> dietTypes;
}
