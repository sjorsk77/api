package com.example.api.mappers;

import com.example.api.dtos.UserDto;
import com.example.api.entities.User;

public class UserMapper {
    public static UserDto toUserDto(User user) {
        return new UserDto() {
            {
                setId(user.getId());
                setUserName(user.getName());
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

    public static User toUser(UserDto userDto) {
        return new User() {
            {
                setId(userDto.getId());
                setName(userDto.getUserName());
                setEmail(userDto.getEmail());
                setPassword(userDto.getPassword());
                setCreatedAt(userDto.getCreatedAt());
                setUpdatedAt(userDto.getUpdatedAt());
                setActive(userDto.isActive());
                setDeleted(userDto.isDeleted());
                setRole(userDto.getRole());
            }
        };
    }
}
