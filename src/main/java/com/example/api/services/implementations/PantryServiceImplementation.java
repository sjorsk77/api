package com.example.api.services.implementations;

import com.example.api.Enums.InvitationStatus;
import com.example.api.dtos.EntityDtos.PantryDto;
import com.example.api.dtos.PantryDtos.CreatePantryDto;
import com.example.api.entities.Pantry;
import com.example.api.entities.PantryInvitation;
import com.example.api.entities.User;
import com.example.api.mappers.PantryMapper;
import com.example.api.repository.PantryInvitationRepository;
import com.example.api.repository.PantryRepository;
import com.example.api.repository.UserRepository;
import com.example.api.services.PantryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PantryServiceImplementation implements PantryService {

    private final PantryRepository pantryRepository;
    private final PantryInvitationRepository pantryInvitationRepository;
    private final UserRepository userRepository;

    @Override
    public Pantry createPantry(CreatePantryDto pantryDto) {

        PantryDto newPantryDto = new PantryDto();

        newPantryDto.setName(pantryDto.getName());
        newPantryDto.setStorageType(pantryDto.getStorageType());

        Pantry newPantry = PantryMapper.toEntity(newPantryDto);
        Pantry savedPantry = pantryRepository.save(newPantry);

        User user = userRepository.findById(pantryDto.getUserId()).get();

        SendSelfInvitation(user, savedPantry);

        return savedPantry;
    }

    @Override
    public List<PantryDto> getPantry(Long userId) {
        List<Pantry> pantries = pantryInvitationRepository.findAcceptedInvitationsByUserId(userId);

        if(pantries != null) { return pantries.stream().map(PantryMapper::toDto).collect(Collectors.toList());}
        return null;
    }

    private void SendSelfInvitation(User user, Pantry pantry) {

        PantryInvitation pantryInvitation = new PantryInvitation();

        pantryInvitation.setInviter(user);
        pantryInvitation.setInvitee(user);
        pantryInvitation.setPantry(pantry);
        pantryInvitation.setStatus(InvitationStatus.ACCEPTED);
        pantryInvitation.setCanInvite(true);

        pantryInvitationRepository.save(pantryInvitation);
    }
}
