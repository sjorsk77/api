package com.example.api.repository;

import com.example.api.entities.Food;
import com.example.api.entities.Pantry;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findByPantry(Pantry pantry);
}
