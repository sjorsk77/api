package com.example.api.services;

import com.example.api.dtos.EntityDtos.UserDto;
import com.example.api.entities.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {
    User createUser(UserDto userDto);
    User getUserById(Long userId);
    List<UserDto> getAllUsers();
    UserDto updateUser(Long userId, UserDto updatedUser);
    void deleteUser(Long userId);
    UserDetails loadUserByUsername(String email);
}
