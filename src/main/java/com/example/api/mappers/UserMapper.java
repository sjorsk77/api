package com.example.api.mappers;

import com.example.api.dtos.EntityDtos.UserDto;
import com.example.api.entities.User;

public class UserMapper {
    public static UserDto toDto(User user) {
        return new UserDto() {
            {
                setId(user.getId());
                setFirstName(user.getFirstName());
                setLastName(user.getLastName());
                setEmail(user.getEmail());
                setPassword(user.getPassword());
                setCreatedAt(user.getCreatedAt());
                setUpdatedAt(user.getUpdatedAt());
                setActive(user.isActive());
                setDeleted(user.isDeleted());
                setRole(user.getRole());
            }
        };
    }

    public static User toEntity(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setCreatedAt(userDto.getCreatedAt());
        user.setUpdatedAt(userDto.getUpdatedAt());
        user.setActive(userDto.isActive());
        user.setDeleted(userDto.isDeleted());
        user.setRole(userDto.getRole());
        return user;
    }
}
