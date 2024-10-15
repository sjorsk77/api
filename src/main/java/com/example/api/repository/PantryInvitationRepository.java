package com.example.api.repository;

import com.example.api.entities.Pantry;
import com.example.api.entities.PantryInvitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PantryInvitationRepository extends JpaRepository<PantryInvitation, Long> {
    @Query("SELECT pi.pantry FROM PantryInvitation pi WHERE pi.invitee.id = :userId AND pi.status = 'ACCEPTED'")
    List<Pantry> findAcceptedInvitationsByUserId(@Param("userId")Long userId);

    @Query("SELECT pi FROM PantryInvitation pi WHERE pi.invitee.id = :inviteeId AND pi.pantry.id = :pantryId AND (pi.status = 'ACCEPTED' OR pi.status = 'PENDING')")
    List<PantryInvitation> findDuplicateInvitation(@Param("inviteeId") Long inviteeId, @Param("pantryId") Long pantryId);

    @Query("SELECT pi FROM PantryInvitation pi WHERE pi.invitee.id = :inviterId AND pi.pantry.id = :pantryId AND (pi.status = 'ACCEPTED' OR pi.status = 'PENDING')")
    PantryInvitation findInviterInvitation(@Param("inviterId") Long inviterId, @Param("pantryId") Long pantryId);
}
