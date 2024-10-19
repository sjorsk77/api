package com.example.api.dtos.EntityDtos;

import com.example.api.entities.DietType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DietDto {
    private Long id;
    private String name;
    private Integer minCalories;
    private Integer maxCalories;
    private List<DietTypeDto> dietTypes;
}
