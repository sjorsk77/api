package com.example.api.entities;

import com.example.api.Enums.Roles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends Base implements UserDetails {

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "riskDays", nullable = false)
    private int riskDays = 3;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted;

    @Enumerated(EnumType.STRING)
    @Column(name="role", nullable = false)
    private Roles role = Roles.USER;


    @OneToMany(mappedBy = "inviter")
    private List<PantryInvitation> sentInvitations = new ArrayList<>();

    @OneToMany(mappedBy = "invitee")
    private List<PantryInvitation> receivedInvitations = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Diet> diets = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isDeleted;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

}
