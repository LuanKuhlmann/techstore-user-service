package io.techstore.user_services.core.port.out;

import io.techstore.user_services.core.domain.model.User;
import io.techstore.user_services.infrastructure.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.UUID;

public interface UserDataPort extends GenericDataPort<UserEntity, UUID>{

    void register(User user);

    Optional<UserDetails> findByMail(String mail);
}
