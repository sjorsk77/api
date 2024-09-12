package com.example.api.dtos;

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
    private String userName;
    private String email;
    private String password;
    private Date createdAt;
    private Date updatedAt;
    private boolean isActive;
    private boolean isDeleted;
    private Roles role;
}
