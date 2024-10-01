package com.example.api.dtos.EntityDtos;

import com.example.api.Enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Roles role;
    private int riskDays;
    private boolean isActive;
    private boolean isDeleted;
    private Date createdAt;
    private Date updatedAt;
}
