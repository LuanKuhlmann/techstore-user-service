package io.techstore.user_services.core.service;

import io.techstore.user_services.core.domain.model.User;
import io.techstore.user_services.core.port.in.UserServicePort;
import io.techstore.user_services.core.port.out.UserDataPort;
import io.techstore.user_services.infrastructure.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService extends AbstractService<UserEntity, UUID, UserDataPort> implements UserServicePort, UserDetailsService {

    @Autowired
    private final CpfValidatorService cpfValidator;
//    @Autowired
//    private final EmailSenderDataPort emailSender;

    @Override
    public void register(final User user) {
        if (!cpfValidator.isValid(user.getCpf())) throw new IllegalArgumentException("CPF inválido");
        this.port.register(user);

        String body = "Cadastro realizado com sucesso!\nNome: " + user.getFirstName() + "\nEmail: " + user.getMail();
//        emailSender.send(user.getMail(), "Cadastro confirmado", body);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.port.findByMail(username).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));
    }
}
