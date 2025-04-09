package io.techstore.user_services.core.port.out;

public interface EmailSenderDataPort {

    void send(String to, String subject, String body);
}
