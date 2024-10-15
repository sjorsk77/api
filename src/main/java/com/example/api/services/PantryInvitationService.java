package com.example.api.services;

import com.example.api.dtos.PantryDtos.PantryInvitationDto;

public interface PantryInvitationService {
    void sendInvitation(PantryInvitationDto pantryInvitationDto);
}
