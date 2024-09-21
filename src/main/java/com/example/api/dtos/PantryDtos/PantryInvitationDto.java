package com.example.api.dtos.PantryDtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PantryInvitationDto {
    @NotNull(message = "pantryId is required")
    private Long pantryId;

    @NotNull(message = "inviterId is required")
    private Long inviterId;

    @NotNull(message = "inviteeId is required")
    private Long inviteeId;
}
