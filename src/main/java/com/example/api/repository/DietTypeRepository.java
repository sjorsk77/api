package com.example.api.repository;

import com.example.api.entities.DietType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DietTypeRepository extends JpaRepository<DietType, Long> {
}
