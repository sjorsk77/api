package com.example.api.controllers;

import com.example.api.dtos.EntityDtos.PantryDto;
import com.example.api.dtos.AccountDtos.RegisterDto;
import com.example.api.dtos.PantryDtos.CreatePantryDto;
import com.example.api.dtos.PantryDtos.PantryInvitationDto;
import com.example.api.entities.Pantry;
import com.example.api.services.PantryInvitationService;
import com.example.api.services.PantryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/pantry")
public class PantryController {

    private final PantryService pantryService;
    private final PantryInvitationService pantryInvitationService;

    @GetMapping("{id}")
    public ResponseEntity<List<PantryDto>> getPantry(@PathVariable("id") Long userId) {
        return ResponseEntity.ok(pantryService.getPantry(userId));
    }

    @PostMapping
    public ResponseEntity<?> createPantry(@Valid @RequestBody CreatePantryDto createPantryDto) {
        Pantry newPantry = pantryService.createPantry(createPantryDto);
        return ResponseEntity.ok(newPantry);
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




}
