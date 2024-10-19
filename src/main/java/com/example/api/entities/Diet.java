package com.example.api.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "diets")
public class Diet extends Base {
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String name;

    @Column(name = "min_cal")
    private Integer minCalories;

    @Column(name = "max_cal")
    private Integer maxCalories;

    @ManyToMany
    @JoinTable(
            name = "diet_diet_type",
            joinColumns = @JoinColumn(name = "diet_id"),
            inverseJoinColumns = @JoinColumn(name = "diet_type_id")
    )
    private List<DietType> dietTypes = new ArrayList<>();
}
