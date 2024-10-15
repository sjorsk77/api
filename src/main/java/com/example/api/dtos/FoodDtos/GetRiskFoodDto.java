package com.example.api.dtos.FoodDtos;

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
public class GetRiskFoodDto {
    @NotBlank(message = "token is required")
    private String token;

    @NotNull(message = "Days to is required")
    private int daysTo;
}
