package io.techstore.user_services.shared.mapper;

import io.techstore.user_services.core.domain.model.User;
import io.techstore.user_services.infrastructure.entity.UserEntity;
import io.techstore.user_services.infrastructure.entity.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserEntityMapper {

    public UserEntity toEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setFirstName(user.getFirstName());
        entity.setLastName(user.getLastName());
        entity.setMail(user.getMail());
        entity.setCpf(user.getCpf());
        entity.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        entity.setRole(defineRole(user.getRole()));
        entity.setActive(user.getActive());
        return entity;
    }

    private UserRole defineRole(String role) {
        return switch (role) {
            case "ADMIN" -> UserRole.ADMIN;
            case "EMPLOYEE" -> UserRole.EMPLOYEE;
            default -> UserRole.CUSTOMER;
        };
    }
}
