package com.example.api.services;

import com.example.api.dtos.EntityDtos.FoodDto;
import com.example.api.dtos.FoodDtos.CreateFoodDto;
import com.example.api.dtos.FoodDtos.GetRiskFoodDto;

import java.util.List;

public interface FoodService {
    FoodDto createFood(CreateFoodDto food);
    List<FoodDto> getFoodsByPantryId(Long PantryId);
    void deleteFoodById(Long foodId);
    List<FoodDto> getRiskFoodsByUserId(GetRiskFoodDto getRiskFoodDto);
}
