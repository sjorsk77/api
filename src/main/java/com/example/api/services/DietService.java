package com.example.api.services;

import com.example.api.dtos.DietDtos.CreateDietRequest;
import com.example.api.dtos.EntityDtos.DietDto;
import com.example.api.dtos.EntityDtos.DietTypeDto;
import com.example.api.entities.Diet;
import com.example.api.entities.DietType;

import java.util.List;

public interface DietService {
    DietDto addDiet(CreateDietRequest diet);
    List<Diet> GetAllDietsByUserId(Long userId);
    void deleteDietById(Long dietId);
    Diet updateDiet(Long dietId, Diet diet);
    List<DietTypeDto> getAllDietTypes();
}
