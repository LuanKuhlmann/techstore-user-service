package io.techstore.user_services.core.port.in;

public interface AuthenticationServicePort {

    String login(String mail, String password);
}
