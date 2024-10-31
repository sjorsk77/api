package com.example.api.mappers;

import com.example.api.dtos.DietDtos.CreateDietRequest;
import com.example.api.dtos.DietDtos.UpdateDietDto;
import com.example.api.dtos.EntityDtos.DietDto;
import com.example.api.entities.Diet;
import com.example.api.entities.DietType;
import com.example.api.entities.User;

import java.util.List;

public class DietMapper {

    public static Diet mapRequestToDiet(User user, CreateDietRequest dietDto) {
        Diet diet = new Diet();
        diet.setUser(user);
        diet.setName(dietDto.getName());
        diet.setMinCalories(dietDto.getMinCalories());
        diet.setMaxCalories(dietDto.getMaxCalories());
        diet.setDietTypes(
                dietDto.getDietTypes() != null
                        ? dietDto.getDietTypes().stream()
                        .map(DietTypeMapper::mapDtoToDietType)
                        .toList()
                        : null
        );
        return diet;
    }

    public static DietDto mapDietToDto(Diet diet) {
        return new
                DietDto(diet.getId(),
                diet.getName(),
                diet.getMinCalories(),
                diet.getMaxCalories(),
                diet.getDietTypes() != null
                        ? diet.getDietTypes().stream()
                        .map(DietTypeMapper::mapDietTypeToDto)
                        .toList()
                        : null
        );
    }

    public static Diet mapUpdateDtoToDiet(UpdateDietDto dietDto, User user) {
        Diet diet = new Diet();
        diet.setId(dietDto.getId());
        diet.setName(dietDto.getName());
        diet.setMinCalories(dietDto.getMinCalories());
        diet.setMaxCalories(dietDto.getMaxCalories());
        diet.setDietTypes(
                dietDto.getDietTypes() != null
                        ? dietDto.getDietTypes().stream()
                        .map(DietTypeMapper::mapDtoToDietType)
                        .toList()
                        : null
        );

        diet.setUser(user);
        return diet;
    }
}
