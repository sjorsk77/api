package com.example.api.mappers;

import com.example.api.dtos.EntityDtos.FoodDto;
import com.example.api.entities.Food;
import com.example.api.entities.Pantry;

public class FoodMapper {
    public static Food toEntity(FoodDto foodDto, Pantry pantry) {
        Food food = new Food();
        food.setPantry(pantry);
        food.setEanCode(foodDto.getEanCode());
        food.setName(foodDto.getName());
        food.setQuantity(foodDto.getQuantity());
        food.setExpiryDate(foodDto.getExpiryDate());
        return food;
    }

    public static FoodDto toDto(Food food) {
        FoodDto FoodDto = new FoodDto();
        FoodDto.setPantry(PantryMapper.toDto(food.getPantry()));
        FoodDto.setId(food.getId());
        FoodDto.setEanCode(food.getEanCode());
        FoodDto.setName(food.getName());
        FoodDto.setQuantity(food.getQuantity());
        FoodDto.setExpiryDate(food.getExpiryDate());
        return FoodDto;
    }
}
