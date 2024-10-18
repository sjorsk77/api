package com.example.api.mappers;

import com.example.api.dtos.DietDtos.CreateDietRequest;
import com.example.api.dtos.EntityDtos.DietDto;
import com.example.api.entities.Diet;
import com.example.api.entities.DietType;
import com.example.api.entities.User;

import java.util.List;

public class DietMapper {

    public static Diet mapRequestToDiet(User user, CreateDietRequest dietDto, List<DietType> dietTypes) {
        Diet diet = new Diet();
        diet.setUser(user);
        diet.setName(dietDto.getName());
        diet.setMinCalories(dietDto.getMinCalories());
        diet.setMaxCalories(dietDto.getMaxCalories());
        diet.setDietTypes(dietTypes);
        return diet;
    }

    public static DietDto mapDietToDto(Diet diet) {
        return new DietDto(diet.getId(), diet.getName(), diet.getMinCalories(), diet.getMaxCalories(), diet.getDietTypes().stream().map(DietTypeMapper::mapDietTypeToDto).toList());
    }
}
