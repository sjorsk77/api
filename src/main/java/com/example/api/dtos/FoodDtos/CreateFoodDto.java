package com.example.api.dtos.FoodDtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateFoodDto {
    @NotNull(message = "pantryId is required")
    private Long pantryId;

    @NotNull(message = "eanCode is required")
    private String eanCode;

    @NotNull(message = "name is required")
    private String name;

    @NotNull(message = "quantity is required")
    private float quantity;

    private Date expiryDate;
}
