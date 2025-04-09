package io.techstore.user_services.core.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    private String firstName;
    private String lastName;
    private String mail;
    private String cpf;
    private String password;
    private Boolean active;
    private String role;
}
