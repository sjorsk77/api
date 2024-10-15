package com.example.api.services;

import com.example.api.dtos.EntityDtos.PantryDto;
import com.example.api.dtos.PantryDtos.CreatePantryDto;
import com.example.api.dtos.PantryDtos.UpdatePantryDto;
import com.example.api.entities.Pantry;

import java.util.List;

public interface PantryService {
    Pantry createPantry(CreatePantryDto pantryDto);
    List<PantryDto> getPantry(Long userId);
    void deletePantry(Long pantryId);
    PantryDto updatePantry(Long id, UpdatePantryDto pantryDto);
    PantryDto getPantryById(Long id);
}
