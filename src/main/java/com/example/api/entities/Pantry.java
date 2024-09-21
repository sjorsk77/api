package com.example.api.entities;

import com.example.api.Enums.StorageTypes;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "pantries")
public class Pantry extends Base {
    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "storage_type", nullable = false)
    private StorageTypes storageType;

    @OneToMany(mappedBy = "pantry", cascade = CascadeType.ALL)
    private List<PantryInvitation> invitations;
}
