package com.example.api.services;

import com.example.api.dtos.AccountDtos.LoginDto;
import com.example.api.dtos.AccountDtos.RegisterDto;
import com.example.api.entities.User;

public interface AccountService {
    User register(RegisterDto signUpDto);
    User authenticate(LoginDto loginDto);
}
