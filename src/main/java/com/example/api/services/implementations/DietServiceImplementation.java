package com.example.api.services.implementations;

import com.example.api.dtos.DietDtos.CreateDietRequest;
import com.example.api.dtos.DietDtos.UpdateDietDto;
import com.example.api.dtos.EntityDtos.DietDto;
import com.example.api.dtos.EntityDtos.DietTypeDto;
import com.example.api.entities.Diet;
import com.example.api.entities.DietType;
import com.example.api.entities.User;
import com.example.api.exceptions.InvalidPropertyValueException;
import com.example.api.exceptions.ResourceNotFoundException;
import com.example.api.mappers.DietMapper;
import com.example.api.mappers.DietTypeMapper;
import com.example.api.repository.DietRepository;
import com.example.api.repository.DietTypeRepository;
import com.example.api.repository.UserRepository;
import com.example.api.services.DietService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class DietServiceImplementation implements DietService {

    private final DietRepository dietRepository;
    private final DietTypeRepository dietTypeRepository;
    private final UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public DietDto addDiet(Diet diet) {
        if(ValidateDiet(diet)){
            dietRepository.save(diet);

            return DietMapper.mapDietToDto(diet);
        } else throw new InvalidPropertyValueException("Request is okay but values are not valid");
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
    @Transactional
    public Diet UpdateDiet(Diet diet) {
        Diet existingDiet = entityManager.find(Diet.class, diet.getId());

        if (existingDiet == null)
            throw new ResourceNotFoundException("Diet with id " + diet.getId() + " not found");

        Diet updatedDiet = CreateUpdatedDiet(existingDiet, diet);

        if(ValidateDiet(updatedDiet)) {
            entityManager.merge(updatedDiet);
            return entityManager.find(Diet.class, diet.getId());
        }
        else
            throw new InvalidPropertyValueException("Request is okay but values are not valid");
    }
    @Override
    public List<DietTypeDto> getAllDietTypes() {
        return dietTypeRepository.findAll().stream().map(DietTypeMapper::mapDietTypeToDto).toList();
    }

    private Diet CreateUpdatedDiet(Diet existingDiet, Diet newDiet) {
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

    private boolean ValidateDiet (Diet diet){
        if(diet.getMaxCalories() < diet.getMinCalories())
            return false;

        if(diet.getMaxCalories() < 0 || diet.getMinCalories() < 0)
            return false;

        return true;
    }
}
