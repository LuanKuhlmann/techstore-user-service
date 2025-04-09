package io.techstore.user_services.infrastructure.adapter;

import io.techstore.user_services.core.port.out.AuthenticationDataPort;
import io.techstore.user_services.infrastructure.config.annotation.Adapter;
import io.techstore.user_services.infrastructure.entity.UserEntity;
import io.techstore.user_services.infrastructure.security.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;

@Adapter
@RequiredArgsConstructor
public class AuthenticationAdapter implements AuthenticationDataPort{

    private final JwtUtils jwtUtils;

    public String authLogin(Authentication authentication) {
        return jwtUtils.generateToken((UserEntity) authentication.getPrincipal());
    }
}
