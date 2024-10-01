package com.example.api.controllers;

import com.example.api.dtos.EntityDtos.FoodDto;
import com.example.api.dtos.FoodDtos.CreateFoodDto;
import com.example.api.dtos.FoodDtos.GetRiskFoodDto;
import com.example.api.services.FoodService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/food")
public class FoodController {

    private final FoodService foodService;

    @PostMapping
    public ResponseEntity<?> createFood(@Valid @RequestBody CreateFoodDto createFoodDto) {
        FoodDto newFood = foodService.createFood(createFoodDto);
        return ResponseEntity.ok(newFood);
    }

    @GetMapping("/{pantryId}")
    public ResponseEntity<?> getFoods(@PathVariable Long pantryId) {
        List<FoodDto> foods = foodService.getFoodsByPantryId(pantryId);
        return ResponseEntity.ok(foods);
    }

    @PostMapping("/risk")
    public ResponseEntity<?> getUserFoods(@Valid @RequestBody GetRiskFoodDto getRiskFoodDto) {
        List<FoodDto> foods = foodService.getRiskFoodsByUserId(getRiskFoodDto);
        return ResponseEntity.ok(foods);
    }

    @DeleteMapping("/{foodId}")
    public ResponseEntity<?> deleteFood(@PathVariable Long foodId) {
        foodService.deleteFoodById(foodId);
        return ResponseEntity.ok().build();
    }
}
