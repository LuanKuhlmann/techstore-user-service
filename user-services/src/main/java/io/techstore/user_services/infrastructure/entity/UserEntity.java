package io.techstore.user_services.infrastructure.entity;

import io.techstore.user_services.infrastructure.entity.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;

@Entity(name = "users")
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String mail;

    @Column(nullable = false, length = 11, unique = true)
    private String cpf;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean active = true;

    @Column(nullable = false)
    private UserRole role;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public void deactivate() {
        this.active = false;
    }

    // ========= Métodos do UserDetails =========

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return switch (this.role) {
            case ADMIN -> List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_EMPLOYEE"),
                    new SimpleGrantedAuthority("ROLE_CONSUMER")
            );
            case EMPLOYEE -> List.of(
                    new SimpleGrantedAuthority("ROLE_EMPLOYEE")
            );
            case CUSTOMER -> List.of(
                    new SimpleGrantedAuthority("ROLE_CONSUMER")
            );
        };
    }

    @Override
    public String getUsername() {
        return mail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}