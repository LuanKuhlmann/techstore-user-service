package io.techstore.user_services.core.port.out;

import org.springframework.security.core.Authentication;

public interface AuthenticationDataPort {

    String authLogin(Authentication authentication);
}
