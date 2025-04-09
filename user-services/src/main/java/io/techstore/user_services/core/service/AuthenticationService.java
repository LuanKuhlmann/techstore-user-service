package io.techstore.user_services.core.service;

import io.techstore.user_services.core.port.in.AuthenticationServicePort;
import io.techstore.user_services.core.port.out.AuthenticationDataPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements AuthenticationServicePort {

    private final AuthenticationManager authenticationManager;
    private final AuthenticationDataPort authenticationDataPort;

    @Override
    public String login (final String mail, final String password) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(mail, password);
        Authentication auth = this.authenticationManager.authenticate(usernamePassword);

        return authenticationDataPort.authLogin(auth);
    }
}
