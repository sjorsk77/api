package com.example.api.services;

import com.example.api.dtos.LoginDto;
import com.example.api.dtos.LoginResponseDto;
import com.example.api.dtos.RegisterDto;
import com.example.api.entities.User;

public interface AccountService {
    User register(RegisterDto signUpDto);
    User authenticate(LoginDto loginDto);
}
