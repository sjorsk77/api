package com.example.api.mappers;

import com.example.api.dtos.EntityDtos.DietTypeDto;
import com.example.api.entities.DietType;

public class DietTypeMapper {
    public static DietTypeDto mapDietTypeToDto(DietType dietType) {
        return new DietTypeDto(dietType.getId(), dietType.getName());
    }
}
