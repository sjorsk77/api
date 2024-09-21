package com.example.api.repository;

import com.example.api.entities.Pantry;
import com.example.api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PantryRepository extends JpaRepository<Pantry, Long> {
}
