package com.example.api.dtos.DietDtos;

import com.example.api.dtos.EntityDtos.DietTypeDto;
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
public class UpdateDietDto {
    @NotNull(message = "Id is required")
    private Long id;
    private String name;
    private Integer minCalories;
    private Integer maxCalories;
    private List<DietTypeDto> dietTypes;
}
