package com.example.api.mappers;

import com.example.api.dtos.EntityDtos.PantryDto;
import com.example.api.entities.Pantry;

public class PantryMapper {
    public static PantryDto toDto(Pantry pantry) {
        return new PantryDto(
                pantry.getId(),
                pantry.getName(),
                pantry.getStorageType(),
                pantry.getCreatedAt(),
                pantry.getUpdatedAt()
        );
    }

    public static Pantry toEntity(PantryDto pantryDto) {
        Pantry pantry = new Pantry();
        pantry.setId(pantryDto.getId());
        pantry.setName(pantryDto.getName());
        pantry.setStorageType(pantryDto.getStorageType());
        pantry.setCreatedAt(pantryDto.getCreatedAt());
        pantry.setUpdatedAt(pantryDto.getUpdatedAt());
        return pantry;
    }
}
