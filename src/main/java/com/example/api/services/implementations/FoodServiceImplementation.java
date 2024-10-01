package com.example.api.services.implementations;

import com.example.api.dtos.EntityDtos.FoodDto;
import com.example.api.dtos.EntityDtos.PantryDto;
import com.example.api.dtos.FoodDtos.CreateFoodDto;
import com.example.api.dtos.FoodDtos.GetRiskFoodDto;
import com.example.api.entities.Food;
import com.example.api.entities.Pantry;
import com.example.api.exceptions.ResourceNotFoundException;
import com.example.api.mappers.FoodMapper;
import com.example.api.mappers.PantryMapper;
import com.example.api.repository.FoodRepository;
import com.example.api.repository.PantryRepository;
import com.example.api.services.FoodService;
import com.example.api.services.JwtService;
import com.example.api.services.PantryService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.token.TokenService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class FoodServiceImplementation implements FoodService {

    private final FoodRepository foodRepository;
    private final PantryRepository pantryRepository;
    private final JwtService jwtService;
    private final PantryService pantryService;

    @Override
    public FoodDto createFood(CreateFoodDto food) {
        Pantry pantry = pantryRepository.findById(food.getPantryId()).orElseThrow(() -> new ResourceNotFoundException("Pantry not found"));

        FoodDto newFoodDto = new FoodDto();
        newFoodDto.setEanCode(food.getEanCode());
        newFoodDto.setName(food.getName());
        newFoodDto.setWeight(food.getWeight());
        newFoodDto.setExpiryDate(food.getExpiryDate());

        Food newFood = FoodMapper.toEntity(newFoodDto, pantry);

        return FoodMapper.toDto(foodRepository.save(newFood));
    }

    @Override
    public List<FoodDto> getFoodsByPantryId(Long pantryId) {
        Pantry pantry = pantryRepository.findById(pantryId).orElseThrow(() -> new ResourceNotFoundException("Pantry not found"));
        List<Food> foods = foodRepository.findByPantry(pantry);

        return foods.stream().map(FoodMapper::toDto).toList();
    }

    @Override
    public void deleteFoodById(Long foodId) {
        Food food = foodRepository.findById(foodId).orElseThrow(() -> new ResourceNotFoundException("Food not found"));
        foodRepository.deleteById(foodId);
    }

    @Override
    public List<FoodDto> getRiskFoodsByUserId(GetRiskFoodDto getRiskFoodDto) {
        Long userId = Long.parseLong(jwtService.ExtractUserId(getRiskFoodDto.getToken()));
        List<PantryDto> pantries = pantryService.getPantry(userId);
        List<Pantry> pantriesEntities = pantries.stream().map(PantryMapper::toEntity).toList();

        List<Food> allFoods = new ArrayList<>();

        for (Pantry pantry : pantriesEntities) {
            List<Food> foods = foodRepository.findByPantry(pantry);
            allFoods.addAll(foods);
        }

        List<Food> riskFoods = getRiskFoods(allFoods, getRiskFoodDto.getDaysTo());

        return riskFoods.stream().map(FoodMapper::toDto).toList();
    }

    private List<Food> getRiskFoods(List<Food> foods, int riskDays) {

        Date now = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.DAY_OF_MONTH, riskDays);
        Date cutoffDate = calendar.getTime();

        return foods.stream()
                .filter(food -> food.getExpiryDate() != null && food.getExpiryDate().before(cutoffDate))
                .collect(Collectors.toList());
    }
}
