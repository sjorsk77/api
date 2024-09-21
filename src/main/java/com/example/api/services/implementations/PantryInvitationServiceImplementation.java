package com.example.api.services.implementations;

import com.example.api.dtos.PantryDtos.PantryInvitationDto;
import com.example.api.entities.PantryInvitation;
import com.example.api.exceptions.CannotSendInvitationException;
import com.example.api.repository.PantryInvitationRepository;
import com.example.api.repository.PantryRepository;
import com.example.api.repository.UserRepository;
import com.example.api.services.PantryInvitationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static java.util.Arrays.stream;

@AllArgsConstructor
@Service
public class PantryInvitationServiceImplementation implements PantryInvitationService {

    UserRepository userRepository;
    PantryInvitationRepository pantryInvitationRepository;
    PantryRepository pantryRepository;

    @Override
    public void sendInvitation(PantryInvitationDto pantryInvitationDto) {

        validateInvitation(pantryInvitationDto);

        PantryInvitation pantryInvitation = new PantryInvitation();

        pantryInvitation.setInviter(userRepository.findById(pantryInvitationDto.getInviterId()).get());
        pantryInvitation.setInvitee(userRepository.findById(pantryInvitationDto.getInviteeId()).get());
        pantryInvitation.setPantry(pantryRepository.findById(pantryInvitationDto.getPantryId()).get());

        pantryInvitationRepository.save(pantryInvitation);
    }

    private void validateInvitation(PantryInvitationDto pantryInvitationDto) {
        if(Objects.equals(pantryInvitationDto.getInviteeId(), pantryInvitationDto.getInviterId())) throw new CannotSendInvitationException("Cannot invite yourself");
        if(pantryInvitationRepository.findAcceptedInvitationsByUserId(pantryInvitationDto.getInviterId()).stream()
                .noneMatch(pantry -> pantry.getId().equals(pantryInvitationDto.getPantryId()))) throw new CannotSendInvitationException("Cannot send request if not in pantry");
        if(!pantryInvitationRepository.findDuplicateInvitation(pantryInvitationDto.getInviteeId(), pantryInvitationDto.getPantryId()).isEmpty()) throw new CannotSendInvitationException("User already invited or accepted");
        if(!pantryInvitationRepository.findInviterInvitation(pantryInvitationDto.getInviterId(), pantryInvitationDto.getPantryId()).isCanInvite()) throw new CannotSendInvitationException("User is not allowed to invite");
    }
}
