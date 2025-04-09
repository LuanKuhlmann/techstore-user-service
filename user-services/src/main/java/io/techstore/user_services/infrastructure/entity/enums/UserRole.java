package io.techstore.user_services.infrastructure.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserRole {
    ADMIN("admin"),
    EMPLOYEE("employee"),
    CUSTOMER("customer");

    private final String role;
}
