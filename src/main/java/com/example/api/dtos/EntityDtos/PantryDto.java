package com.example.api.dtos.EntityDtos;

import com.example.api.Enums.StorageTypes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PantryDto {
    private Long id;
    private String name;
    private StorageTypes storageType;
    private Date createdAt;
    private Date updatedAt;
}
