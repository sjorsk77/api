package com.example.api.services.implementations;

import com.example.api.dtos.DietDtos.CreateDietRequest;
import com.example.api.dtos.DietDtos.UpdateDietDto;
import com.example.api.dtos.EntityDtos.DietDto;
import com.example.api.dtos.EntityDtos.DietTypeDto;
import com.example.api.entities.Diet;
import com.example.api.entities.DietType;
import com.example.api.entities.User;
import com.example.api.exceptions.ResourceNotFoundException;
import com.example.api.mappers.DietMapper;
import com.example.api.mappers.DietTypeMapper;
import com.example.api.repository.DietRepository;
import com.example.api.repository.DietTypeRepository;
import com.example.api.repository.UserRepository;
import com.example.api.services.DietService;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class DietServiceImplementation implements DietService {

    private final DietRepository dietRepository;
    private final DietTypeRepository dietTypeRepository;
    private final UserRepository userRepository;


    @Override
    public DietDto addDiet(Diet diet) {
        dietRepository.save(diet);

        return DietMapper.mapDietToDto(diet);
    }

    @Override
    public List<Diet> GetAllDietsByUserId(Long userId) {
        return dietRepository.findByUserId(userId);
    }

    @Override
    public void deleteDietById(Long dietId) {
        dietRepository.deleteById(dietId);
    }

    @Override
    public Diet UpdateDiet(Diet diet) {
        Diet dietToUpdate = dietRepository.findById(diet.getId()).orElseThrow(() -> new ResourceNotFoundException("Diet not found"));

        try {
            return dietRepository.save(diet);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Diet not found");
        }

    }

    @Override
    public List<DietTypeDto> getAllDietTypes() {
        return dietTypeRepository.findAll().stream().map(DietTypeMapper::mapDietTypeToDto).toList();
    }

    private Diet UpdateDiet(Diet existingDiet, Diet newDiet) {
        if (newDiet.getName() != null) {
            existingDiet.setName(newDiet.getName());
        }
        if (newDiet.getMinCalories() != null) {
            existingDiet.setMinCalories(newDiet.getMinCalories());
        }
        if (newDiet.getMaxCalories() != null) {
            existingDiet.setMaxCalories(newDiet.getMaxCalories());
        }
        if (newDiet.getDietTypes() != null) {
            existingDiet.setDietTypes(newDiet.getDietTypes());
        }
        return existingDiet;
    }
}
