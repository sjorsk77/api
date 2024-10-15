package com.example.api.dtos.EntityDtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FoodDto {
    private Long id;
    private PantryDto pantry;
    private String eanCode;
    private String name;
    private float quantity;
    private Date expiryDate;
}
