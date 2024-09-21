package com.example.api.entities;

import com.example.api.Enums.InvitationStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pantry_invitations")
public class PantryInvitation extends Base {

    @ManyToOne
    @JoinColumn(name="inviter_id", nullable = false)
    private User inviter;

    @ManyToOne
    @JoinColumn(name="invitee_id", nullable = false)
    private User invitee;

    @ManyToOne
    @JoinColumn(name="pantry_id", nullable = false)
    private Pantry pantry;

    @Column(name="status", nullable = false)
    @Enumerated(EnumType.STRING)
    private InvitationStatus status = InvitationStatus.PENDING;

    @Column(name="can_invite", nullable = false)
    private boolean canInvite = false;
}
