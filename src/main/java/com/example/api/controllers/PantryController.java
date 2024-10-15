package com.example.api.controllers;

import com.example.api.dtos.EntityDtos.PantryDto;
import com.example.api.dtos.AccountDtos.RegisterDto;
import com.example.api.dtos.PantryDtos.CreatePantryDto;
import com.example.api.dtos.PantryDtos.PantryInvitationDto;
import com.example.api.dtos.PantryDtos.UpdatePantryDto;
import com.example.api.entities.Pantry;
import com.example.api.services.JwtService;
import com.example.api.services.PantryInvitationService;
import com.example.api.services.PantryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/pantry")
public class PantryController {

    private final PantryService pantryService;
    private final PantryInvitationService pantryInvitationService;
    private final JwtService jwtService;

    @GetMapping("{token}")
    public ResponseEntity<List<PantryDto>> getPantry(@PathVariable("token") String token) {

        Long userId = Long.parseLong(jwtService.ExtractUserId(token));
        return ResponseEntity.ok(pantryService.getPantry(userId));
    }

    @PostMapping
    public ResponseEntity<?> createPantry(@Valid @RequestBody CreatePantryDto createPantryDto) {
        Pantry newPantry = pantryService.createPantry(createPantryDto);
        return ResponseEntity.created(null).body(newPantry);
    }

    @PostMapping("/invite")
    public ResponseEntity inviteToPantry(@Valid @RequestBody PantryInvitationDto pantryInvitationDto) {
        try {
            pantryInvitationService.sendInvitation(pantryInvitationDto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok("Invitation sent successfully");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePantry(@PathVariable("id") Long id) {
        pantryService.deletePantry(id);
        return ResponseEntity.ok("Pantry deleted successfully");
    }

    @PutMapping("{id}")
    public ResponseEntity<PantryDto> updatePantry(@PathVariable("id") Long id, @Valid @RequestBody UpdatePantryDto pantryDto) {
        PantryDto pantry = pantryService.updatePantry(id, pantryDto);
        return ResponseEntity.ok(pantry);
    }

    @GetMapping("/single/{pantryId}")
    public ResponseEntity<PantryDto> getPantryById(@PathVariable("pantryId") Long pantryId) {
        PantryDto pantry = pantryService.getPantryById(pantryId);
        return ResponseEntity.ok(pantry);
    }




}
