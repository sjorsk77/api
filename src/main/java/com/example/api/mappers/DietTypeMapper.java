package com.example.api.mappers;

import com.example.api.dtos.EntityDtos.DietTypeDto;
import com.example.api.entities.DietType;

public class DietTypeMapper {
    public static DietTypeDto mapDietTypeToDto(DietType dietType) {
        return new DietTypeDto(dietType.getId(), dietType.getName());
    }

    public static DietType mapDtoToDietType(DietTypeDto dietTypeDto) {
        DietType dietType = new DietType();
        dietType.setId(dietTypeDto.getId());
        dietType.setName(dietTypeDto.getName());
        return dietType;
    }
}
