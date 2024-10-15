package com.example.api.controllers;

import com.example.api.dtos.EntityDtos.UserDto;
import com.example.api.entities.User;
import com.example.api.mappers.UserMapper;
import com.example.api.services.JwtService;
import com.example.api.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.token.TokenService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private JwtService jwtService;

    //Get user by id
    @GetMapping("{token}")
    public ResponseEntity<UserDto> getUserByToken(@PathVariable("token") String token) {
        Long userId = Long.parseLong(jwtService.ExtractUserId(token));

        User user = userService.getUserById(userId);

        return new ResponseEntity<>(UserMapper.toDto(user), HttpStatus.OK);
    }

    //Get all users
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    //Update user by id
    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId,
                                              @RequestBody UserDto updatedUser) {
        UserDto user = userService.updateUser(userId, updatedUser);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //Delete user by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("user deleted successfully");
    }
}
