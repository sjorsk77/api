package com.example.api.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "foods")
public class Food extends Base {
    @ManyToOne
    @JoinColumn(name = "pantry_id", nullable = false)
    private Pantry pantry;

    @Column(name = "ean_code", length = 255)
    private String eanCode;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "quantity")
    private Float quantity;

    @Column(name = "expiry_date")
    private Date expiryDate;
}
