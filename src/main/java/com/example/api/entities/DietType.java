package com.example.api.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "diet_types")
public class DietType extends Base {

    @Column(unique = true, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "dietTypes")
    private List<Diet> diets = new ArrayList<>();
}
