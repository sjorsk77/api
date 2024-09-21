package com.example.api.controllers;

import com.example.api.dtos.AccountDtos.LoginDto;
import com.example.api.dtos.AccountDtos.LoginResponseDto;
import com.example.api.dtos.AccountDtos.RegisterDto;
import com.example.api.entities.User;
import com.example.api.services.AccountService;
import com.example.api.services.JwtService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/account")
public class AccountController {

    private AccountService accountService;
    private JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<?> getAccount(@Valid @RequestBody RegisterDto registerDto) {
        User new_user = accountService.register(registerDto);
        return ResponseEntity.ok(new_user);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginDto loginDto) {

        User user = accountService.authenticate(loginDto);
        String token = jwtService.generateToken(user);

        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setToken(token);
        loginResponseDto.setId(user.getId());
        loginResponseDto.setExpiresAt(jwtService.ExtractExpiration(token));
        loginResponseDto.setRole(user.getRole());

        return new ResponseEntity<>(loginResponseDto, HttpStatus.OK);
    }
}
