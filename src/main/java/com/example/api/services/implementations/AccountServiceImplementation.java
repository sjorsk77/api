package com.example.api.services.implementations;

import com.example.api.dtos.AccountDtos.LoginDto;
import com.example.api.dtos.AccountDtos.RegisterDto;
import com.example.api.entities.User;
import com.example.api.exceptions.EmailAlreadyExistsException;
import com.example.api.exceptions.PasswordMismatchException;
import com.example.api.exceptions.ResourceNotFoundException;
import com.example.api.repository.UserRepository;
import com.example.api.services.AccountService;
import com.example.api.services.Internal.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class AccountServiceImplementation implements AccountService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    @Override
    public User register(RegisterDto registerDto) {

        if(!registerDto.getPassword().equals(registerDto.getConfirmPassword())) {
            throw new PasswordMismatchException("Passwords do not match");
        }

        if(userRepository.existsByEmail(registerDto.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        String hashedPassword = passwordEncoder.encode(registerDto.getPassword());

        User user = new User();
        user.setFirstName(registerDto.getFirstName());
        user.setLastName(registerDto.getLastName());
        user.setEmail(registerDto.getEmail());
        user.setPassword(hashedPassword);

        userRepository.save(user);

        emailService.sendVerificationEmail(user.getEmail(), user.getId());

        return user;
    }

    @Override
    public User authenticate(LoginDto loginDto) {
        Optional<User> user = userRepository.findByEmail(loginDto.getEmail());

        if(user.isEmpty()) throw new ResourceNotFoundException("User not found");

        if(!passwordEncoder.matches(loginDto.getPassword(), user.get().getPassword())) throw new PasswordMismatchException("Password is incorrect");

        if(!user.get().isActive()) throw new ResourceNotFoundException("User is not active, please verify your account via email");

        return user.get();
    }

    @Override
    public void verifyAccount(Long userId) {
        Optional<User> user = userRepository.findById(userId);

        if(user.isEmpty()) throw new ResourceNotFoundException("User not found");

        user.get().setActive(true);

        userRepository.save(user.get());
    }
}
