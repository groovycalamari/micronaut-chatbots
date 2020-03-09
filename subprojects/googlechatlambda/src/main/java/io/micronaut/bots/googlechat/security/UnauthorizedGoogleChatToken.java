package io.micronaut.bots.googlechat.security;

public class UnauthorizedGoogleChatToken extends RuntimeException {

    public UnauthorizedGoogleChatToken(String msg) {
        super(msg);
    }
}
