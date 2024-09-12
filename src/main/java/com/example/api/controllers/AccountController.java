package com.example.api.controllers;

import com.example.api.dtos.RegisterDto;
import com.example.api.entities.User;
import com.example.api.exceptions.EmailAlreadyExistsException;
import com.example.api.exceptions.PasswordMismatchException;
import com.example.api.services.AccountService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/account")
public class AccountController {

    private AccountService accountService;



    @PostMapping("/register")
    public ResponseEntity<?> getAccount(@Valid @RequestBody RegisterDto registerDto) {
        User new_user = accountService.register(registerDto);
        return ResponseEntity.ok(new_user);
    }

    @PostMapping("/login")
    public String login() {
        return "Login";
    }
}
