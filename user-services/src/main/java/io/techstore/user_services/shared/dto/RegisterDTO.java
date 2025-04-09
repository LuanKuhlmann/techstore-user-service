package io.techstore.user_services.shared.dto;

public record RegisterDTO(
        String firstName,
        String lastName,
        String mail,
        String cpf,
        String password,
        Boolean active,
        String role
) {
}
