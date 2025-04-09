package io.techstore.user_services.infrastructure.adapter;

import io.techstore.user_services.core.domain.model.User;
import io.techstore.user_services.core.port.out.UserDataPort;
import io.techstore.user_services.infrastructure.entity.UserEntity;
import io.techstore.user_services.infrastructure.config.annotation.Adapter;
import io.techstore.user_services.shared.mapper.UserEntityMapper;
import io.techstore.user_services.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.UUID;

@Adapter
@RequiredArgsConstructor
public class UserRepositoryAdapter extends AbstractRepositoryAdapter<UserEntity, UUID, UserRepository> implements UserDataPort {

    @Autowired
    private final UserEntityMapper userEntityMapper;

    @Override
    public void register(User user) {
        this.repository.save(userEntityMapper.toEntity(user));
    }

    @Override
    public Optional<UserDetails> findByMail(String mail) {
        return this.repository.findByMail(mail);
    }
}
