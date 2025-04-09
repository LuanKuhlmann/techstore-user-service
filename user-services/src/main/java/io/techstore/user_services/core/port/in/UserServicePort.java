package io.techstore.user_services.core.port.in;

import io.techstore.user_services.core.domain.model.User;
import io.techstore.user_services.infrastructure.entity.UserEntity;

import java.util.UUID;

public interface UserServicePort extends GenericServicePort<UserEntity, UUID>{

    void register(final User user);
}
