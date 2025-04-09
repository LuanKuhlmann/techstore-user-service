package io.techstore.user_services.shared.mapper;

import io.techstore.user_services.core.domain.model.User;
import io.techstore.user_services.shared.dto.RegisterDTO;
import org.springframework.stereotype.Component;

@Component
public class UserModelMapper {

    public User registerDtoToModel(RegisterDTO registerDTO) {
        User user = new User();
        user.setFirstName(registerDTO.firstName());
        user.setLastName(registerDTO.lastName());
        user.setMail(registerDTO.mail());
        user.setCpf(registerDTO.cpf());
        user.setPassword(registerDTO.password());
        user.setRole(registerDTO.role());
        user.setActive(registerDTO.active());
        return user;
    }
}
