package com.example.api.controllers;

import com.example.api.dtos.DietDtos.CreateDietRequest;
import com.example.api.dtos.DietDtos.UpdateDietDto;
import com.example.api.dtos.EntityDtos.DietDto;
import com.example.api.entities.Diet;
import com.example.api.entities.User;
import com.example.api.mappers.DietMapper;
import com.example.api.mappers.DietTypeMapper;
import com.example.api.services.DietService;
import com.example.api.services.JwtService;
import com.example.api.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.token.TokenService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/diet")
public class DietController {

    private final UserService userService;
    private final DietService dietService;
    private final JwtService jwtService;

    @PostMapping("/create")
    public ResponseEntity<DietDto> createDiet(@Valid @RequestBody CreateDietRequest createDietDto, HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7); // Remove "Bearer " prefix
        }

        Long userId = Long.parseLong(jwtService.ExtractUserId(token));
        User user = userService.getUserById(userId);

        Diet diet = DietMapper.mapRequestToDiet(user, createDietDto);

        return ResponseEntity.ok(dietService.addDiet(diet));
    }

    @GetMapping("/{token}")
    public ResponseEntity<List<DietDto>> getDiet(@PathVariable("token") String Token) {
        Long userId = Long.parseLong(jwtService.ExtractUserId(Token));
        List<Diet> diets = dietService.GetAllDietsByUserId(userId);

        List<DietDto> dietsToDto = diets.stream().map(DietMapper::mapDietToDto).toList();

        return ResponseEntity.ok(dietsToDto);
    }

    @DeleteMapping("/{dietId}")
    public ResponseEntity<?> deleteDiet(@PathVariable Long dietId) {
        dietService.deleteDietById(dietId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{dietId}")
    public ResponseEntity<DietDto> updateDiet(@PathVariable Long dietId, @RequestBody UpdateDietDto updateDietDto, HttpServletRequest request) {

        String token = request.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7); // Remove "Bearer " prefix
        }

        Long userId = Long.parseLong(jwtService.ExtractUserId(token));

        User user;
        try {
            user = userService.getUserById(userId);
        } catch (Exception e) {
            user = null;
        }

        Diet diet = DietMapper.mapUpdateDtoToDiet(updateDietDto, user);

        dietService.UpdateDiet(diet);

        return ResponseEntity.ok(DietMapper.mapDietToDto(diet));
    }

    @GetMapping("/types")
    public ResponseEntity<?> getDietTypes() {
        return ResponseEntity.ok(dietService.getAllDietTypes());
    }
}
